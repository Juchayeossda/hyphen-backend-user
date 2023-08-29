package com.get.hyphenbackenduser.global.lib.jwt.filter;

import com.get.hyphenbackenduser.global.exception.global.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (InvalidTokenException unauthorizedException) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, unauthorizedException);
        } catch (ExpiredJwtException expiredJwtException) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, expiredJwtException);
        } catch (Exception e) {
            setErrorResponse(HttpStatus.BAD_REQUEST, response, e);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) throws IOException {
        response.setStatus(status.value());
    }
}