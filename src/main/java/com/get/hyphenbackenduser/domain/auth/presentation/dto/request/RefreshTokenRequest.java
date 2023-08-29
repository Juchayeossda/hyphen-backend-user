package com.get.hyphenbackenduser.domain.auth.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(title = "RefreshTokenRequest: 토큰 재발급 요청 Dto", description = "액세스 토큰 재발급 요청 객체")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenRequest {

    @Schema(description = "리프레시 토큰")
    @NotNull(message = "refresh token can not be null.")
    private String refreshToken;
}