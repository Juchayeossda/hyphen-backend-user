package com.get.hyphenbackenduser.domain.user.mapper;

import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.UserDTO;
import com.get.hyphenbackenduser.domain.user.enums.UserRole;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User createEntity(UserDTO dto) {
        return User.builder()
                .uid(dto.getUid())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .userStatus(UserStatus.DEACTIVATED)
                .userRole(UserRole.MEMBER)
                .build();
    }
}
