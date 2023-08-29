package com.get.hyphenbackenduser.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    ACTIVE("STATUS_ACTIVE"), DEACTIVATED("STATUS_DEACTIVATED"), BANNED("STATUS_BANNED");
    private final String key;
}
