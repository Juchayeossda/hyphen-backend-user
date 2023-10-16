package com.get.hyphenbackenduser.domain.inquiry.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryStatus {

    NO_ANSWER("NO_ANSWER"), ANSWERS_PRESENT("ANSWERS_PRESENT");
    private final String key;
}