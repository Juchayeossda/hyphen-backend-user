package com.get.hyphenbackenduser.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.get.hyphenbackenduser.global.lib.jwt.properties.JwtToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.SuperBuilder;

@Schema(title = "RefreshTokenResponse: 토큰 재발급 응답 Dto", description = "액세스 토큰 재발급 응답 객체")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RefreshTokenResponse extends JwtToken {

}
