package com.get.hyphenbackenduser.domain.auth.exception;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyLogoutedUserException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyLogoutedUserException();

    private AlreadyLogoutedUserException() {
        super(HttpStatus.FORBIDDEN, "이미 로그아웃된 계정입니다.");
    }
}


