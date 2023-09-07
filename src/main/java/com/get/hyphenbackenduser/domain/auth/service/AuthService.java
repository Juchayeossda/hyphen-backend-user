package com.get.hyphenbackenduser.domain.auth.service;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.request.LoginRequest;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.UserDTO;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.LogoutResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.RegisterResponse;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;

public interface AuthService {

    LoginTokenResponse authenticate(LoginRequest loginRequest);
    RegisterResponse register(UserDTO userDTO);
    LogoutResponse logout();
}
