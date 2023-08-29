package com.get.hyphenbackenduser.domain.auth.exception;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyWithdrawnUserException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyWithdrawnUserException();

    private AlreadyWithdrawnUserException() {
        super(HttpStatus.FORBIDDEN, "이미 탈퇴했거나 정지된 계정입니다.");
    }
}


