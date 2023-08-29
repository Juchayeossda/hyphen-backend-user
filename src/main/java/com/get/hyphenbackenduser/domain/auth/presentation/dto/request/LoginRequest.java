package com.get.hyphenbackenduser.domain.auth.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Schema(title = "LoginRequest: 로그인 요청 Dto", description = "로그인 요청 객체")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @Schema(description = "사용자 아이디")
    @NotEmpty(message = "uid can not be empty.")
    @Size(min=8, max=16, message = "uid must be at least 8 characters long and not more than 16 characters long.")
    private String uid;

    @Schema(description = "사용자 패스워드")
    @NotEmpty(message = "password can not be empty.")
    @Size(min=8, max=24, message = "password must be at least 8 characters long and not more than 24 characters long.")
    private String password;
}
