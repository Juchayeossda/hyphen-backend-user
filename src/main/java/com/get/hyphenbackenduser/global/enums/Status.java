package com.get.hyphenbackenduser.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    SUCCESS("STATUS_SUCCESS"), FAILURE("STATUS_FAILURE");
    private final String key;
}
