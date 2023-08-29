package com.get.hyphenbackenduser.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.get.hyphenbackenduser.global.lib.jwt.properties.JwtToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.SuperBuilder;

@Schema(title = "LoginTokenResponse: 로그인 응답 Dto", description = "로그인 토큰 응답 객체")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginTokenResponse extends JwtToken {

}