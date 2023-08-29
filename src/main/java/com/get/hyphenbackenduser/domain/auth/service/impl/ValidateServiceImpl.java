package com.get.hyphenbackenduser.domain.auth.service.impl;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.AuthUserResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.RefreshTokenResponse;
import com.get.hyphenbackenduser.domain.auth.service.ValidateService;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.global.exception.global.CredentialsNotFoundException;
import com.get.hyphenbackenduser.global.lib.jwt.JwtProvider;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final SecurityService securityService;
    private final JwtProvider jwtProvider;

    public RefreshTokenResponse refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.equals("")) {
            throw CredentialsNotFoundException.EXCEPTION;
        }
        return jwtProvider.refreshToken(refreshToken);
    }

    public AuthUserResponse getUAuthUserInfo() {
        User user = securityService.getAuthUserInfo();
        return AuthUserResponse.builder()
                .uid(user.getUid())
                .userStatus(user.getUserStatus())
                .userRole(user.getUserRole())
                .build();
    }
}
