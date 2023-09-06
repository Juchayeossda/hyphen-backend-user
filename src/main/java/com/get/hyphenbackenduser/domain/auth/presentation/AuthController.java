package com.get.hyphenbackenduser.domain.auth.presentation;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.request.LoginRequest;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.request.RegisterRequest;
import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.LoginTokenResponse;
import com.get.hyphenbackenduser.domain.auth.service.AuthService;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
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
import org.springframework.web.bind.annotation.*;

// RestController("/api/auth")
@Tag(name = "AuthController", description = "회원 인증 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST("/signin")
    @Operation(method = "POST", summary = "로그인 API", description = "회원 인증 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LoginTokenResponse.class),
                            examples = @ExampleObject(value = "{" +
                                    "\"accessToken\":\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290X2FkbWluIiwiaWF0IjoxNjkyNjkyNDU4LCJleHAiOjE2OTI2OTYwNTh9.UzLNdI5Me9_5hXNUq1TIceQRRD6EBYd6pYLQ4X85G0A\"," +
                                    "\"refreshToken\":\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290X2FkbWluIiwiaWF0IjoxNjkyNjkyNDU4LCJleHAiOjE2OTM5MDIwNTh9.ZDu1Q-5CeCLQ1zBdxTBajOI4wgiYd4ZbRTyNcft2Dhs\"" +
                                    "}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "로그인 정보",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LoginRequest.class),
                    examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"password\":\"hyun234**\"}")
            )
    )
    @PostMapping("/signin")
    public ResponseEntity<LoginTokenResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    // POST("/signup")
    @Operation(method = "POST", summary = "회원가입 API", description = "회원 추가 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"uid\":\"hyun234\",\"name\":\"Hyun\",\"email\":\"hyun@email.com\",\"userStatus\":\"DEACTIVATED\",\"userRole\":\"MEMBER\",\"socialType\":null,\"socialId\":null,\"imageId\":null,\"createdDateTime\":\"2023-08-22 17:20:49\",\"modifiedDateTime\":\"2023-08-22 17:24:26\",\"deletedDateTime\":null}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "회원가입 정보",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RegisterRequest.class),
                    examples = @ExampleObject(value = "{\"uid\":\"hyun234\",\"name\":\"Hyun\",\"email\":\"hyun@email.com\",\"password\":\"hyun234**\"}")
            )
    )
    @PostMapping("/signup")
    public ResponseEntity<User> register(@Validated @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest.toUserDTO()));
    }

    // POST("/signout")
    @Operation(method = "POST", summary = "로그아웃 API", description = "회원 로그아웃 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(value = "{\"id\":1,\"uid\":\"hyun234\",\"name\":\"Hyun\",\"email\":\"hyun@email.com\",\"userStatus\":\"DEACTIVATED\",\"userRole\":\"MEMBER\",\"socialType\":null,\"socialId\":null,\"imageId\":null,\"createdDateTime\":\"2023-08-22 17:20:49\",\"modifiedDateTime\":\"2023-08-22 17:24:26\",\"deletedDateTime\":null}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @PostMapping("/signout")
    public ResponseEntity<User> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}
