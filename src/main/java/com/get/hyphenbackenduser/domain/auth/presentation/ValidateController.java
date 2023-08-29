package com.get.hyphenbackenduser.domain.auth.presentation;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.request.RefreshTokenRequest;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.AuthUserResponse;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.RefreshTokenResponse;
import com.get.hyphenbackenduser.domain.auth.service.ValidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController("/api/auth/jwt")
@Tag(name = "ValidateController", description = "회원 인가 API")
@RestController
@RequestMapping("/api/auth/jwt")
@RequiredArgsConstructor
public class ValidateController {

    private final ValidateService validateService;

    // POST("/validate")
    @Operation(method = "POST", summary = "인가 API", description = "회원 인가 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthUserResponse.class),
                            examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"userStatus\":\"ACTIVE\",\"userRole\":\"MEMBER\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @PostMapping("/validate")
    public ResponseEntity<AuthUserResponse> certification() {
        return ResponseEntity.ok(validateService.getUAuthUserInfo());
    }

    // POST("/refresh")
    @Operation(method = "POST", summary = "토큰 발급 API", description = "액세스 토큰 발급 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RefreshTokenResponse.class),
                            examples = @ExampleObject(value = "{\"accessToken\":\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290X2FkbWluIiwiaWF0IjoxNjkyNjkyNDU4LCJleHAiOjE2OTI2OTYwNTh9.UzLNdI5Me9_5hXNUq1TIceQRRD6EBYd6pYLQ4X85G0A\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "리프레시 토큰",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RefreshTokenRequest.class),
                    examples = @ExampleObject(value = "{\"refreshToken\":\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290X2FkbWluIiwiaWF0IjoxNjkyNjkyNDU4LCJleHAiOjE2OTM5MDIwNTh9.ZDu1Q-5CeCLQ1zBdxTBajOI4wgiYd4ZbRTyNcft2Dhs\"}")
            )
    )
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(validateService.refreshToken(refreshTokenRequest.getRefreshToken()));
    }
}
