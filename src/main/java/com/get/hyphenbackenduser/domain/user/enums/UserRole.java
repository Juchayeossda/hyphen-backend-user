package com.get.hyphenbackenduser.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    GUEST("ROLE_GUEST"), MEMBER("ROLE_MEMBER"), MANAGER("ROLE_MANGER"), ADMIN("ROLE_ADMIN");
    private final String key;
}