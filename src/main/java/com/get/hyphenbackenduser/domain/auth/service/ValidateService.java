package com.get.hyphenbackenduser.domain.auth.service;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.AuthUserResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.RefreshTokenResponse;

public interface ValidateService {

    RefreshTokenResponse refreshToken(String refreshToken);
    AuthUserResponse getUAuthUserInfo();
}
