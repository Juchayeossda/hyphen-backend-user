package com.get.hyphenbackenduser.domain.user.exception;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends CustomException {

    public static final CustomException EXCEPTION = new NotFoundUserException();

    private NotFoundUserException() {
        super(HttpStatus.NOT_FOUND, "잘못된 회원 정보입니다.");
    }

}
