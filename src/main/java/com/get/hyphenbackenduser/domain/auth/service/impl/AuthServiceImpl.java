package com.get.hyphenbackenduser.domain.auth.service.impl;

import com.get.hyphenbackenduser.domain.auth.exception.AlreadyLoginedUserException;
import com.get.hyphenbackenduser.domain.auth.exception.AlreadyLogoutedUserException;
import com.get.hyphenbackenduser.domain.auth.exception.AlreadyLogupedUserException;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.UserDTO;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.request.LoginRequest;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.LogoutResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.RegisterResponse;
import com.get.hyphenbackenduser.domain.auth.service.AuthService;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.domain.user.domain.repository.UserRepository;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import com.get.hyphenbackenduser.domain.user.exception.UserDeactivatedException;
import com.get.hyphenbackenduser.domain.user.mapper.UserMapper;
import com.get.hyphenbackenduser.global.enums.Status;
import com.get.hyphenbackenduser.global.lib.jwt.JwtProvider;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final SecurityService securityService;

    public LoginTokenResponse authenticate(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUid(), loginRequest.getPassword()));
        User user = securityService.getAuthUserInfo(authenticate);
        if (user.getUserStatus().equals(UserStatus.ACTIVE)) {
            throw AlreadyLoginedUserException.EXCEPTION;
        }
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw UserDeactivatedException.EXCEPTION;
        }
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        return jwtProvider.createToken((UserDetails) authenticate.getPrincipal());
    }

    @Transactional
    public RegisterResponse register(UserDTO userDTO) {
        if (userRepository.findByUid(userDTO.getUid()).orElse(null) != null) {
            throw AlreadyLogupedUserException.EXCEPTION;
        }
        User user = userRepository.save(userMapper.createEntity(userDTO));
        if (user != null) {
            return RegisterResponse.builder()
                    .uid(user.getUid())
                    .userStatus(user.getUserStatus())
                    .userRole(user.getUserRole())
                    .status(Status.SUCCESS)
                    .build();
        } else {
            return RegisterResponse.builder()
                    .status(Status.FAILURE)
                    .build();
        }
    }

    @Transactional
    public LogoutResponse logout() {
        User user = securityService.getAuthUserInfo();
        if (user.getUserStatus().equals(UserStatus.DEACTIVATED)) {
            throw AlreadyLogoutedUserException.EXCEPTION;
        }
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw UserDeactivatedException.EXCEPTION;
        }
        user.setUserStatus(UserStatus.DEACTIVATED);
        Status status;
        if (userRepository.save(user) != null) {
            status = Status.SUCCESS;
        } else {
            status = Status.FAILURE;
        }
        return LogoutResponse.builder()
                .uid(user.getUid())
                .userStatus(user.getUserStatus())
                .status(status)
                .build();
    }
}
