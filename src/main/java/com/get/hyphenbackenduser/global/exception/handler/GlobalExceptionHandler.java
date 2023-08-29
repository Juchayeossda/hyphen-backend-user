package com.get.hyphenbackenduser.global.exception.handler;

import com.get.hyphenbackenduser.global.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handleCustomException(CustomException e) {
        return null;
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity handleException(BadCredentialsException e) {
        return null;
    }
}
