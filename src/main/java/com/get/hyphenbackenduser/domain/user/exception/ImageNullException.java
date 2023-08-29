package com.get.hyphenbackenduser.domain.user.exception;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ImageNullException extends CustomException {

    public static final CustomException EXCEPTION = new ImageNullException();

    private ImageNullException() {
        super(HttpStatus.NOT_FOUND, "이미지가 비어있습니다.");
    }
}
