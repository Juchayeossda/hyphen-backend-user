package com.get.hyphenbackenduser.domain.auth.exception;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyLoginedUserException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyLoginedUserException();

    private AlreadyLoginedUserException() {
        super(HttpStatus.FORBIDDEN, "이미 로그인된 계정입니다.");
    }
}


