package com.get.hyphenbackenduser.domain.user.exception;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ImageNotFoundException();

    private ImageNotFoundException() {
        super(HttpStatus.NOT_FOUND, "이미지가 존재하지 않습니다.");
    }
}