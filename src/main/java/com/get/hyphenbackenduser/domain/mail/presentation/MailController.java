package com.get.hyphenbackenduser.domain.mail.presentation;

import com.get.hyphenbackenduser.domain.mail.presentation.dto.request.MailRequest;
import com.get.hyphenbackenduser.domain.mail.presentation.dto.response.MailResponse;
import com.get.hyphenbackenduser.domain.mail.service.MailService;
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

// MailController("/api/auth")
@Tag(name = "MailController", description = "메일 인증 API")
@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    // POST("/validate")
    @Operation(method = "POST", summary = "이메일 인증 API", description = "회원 이메일 인증 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "<b>[Success]</b> 스캔 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MailResponse.class),
                            examples = @ExampleObject(value = "{\"sendEmail\":\"sender@email.com\",\"recvEmail\":\"hyun@email.com\",\"authNumber\":\"9EASRdn6\"}")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "<b>[Unauthorized]</b> 인가 기능이 확인되지 않은 접근", content = @Content),
            @ApiResponse(responseCode = "404", description = "<b>[NotFound]</b> 존재하지 않는 리소스 접근", content = @Content),
            @ApiResponse(responseCode = "500", description = "<b>[InternalError]</b> 서버 오류 발생", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "이메일",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MailRequest.class),
                    examples = @ExampleObject(value = "{\"email\":\"hyun@email.com\"}")
            )
    )
    @PostMapping("/validate")
    public ResponseEntity<MailResponse> emailAuth(@Validated @RequestBody MailRequest mailRequest) throws Exception {
        return ResponseEntity.ok(mailService.sendMessage(mailRequest.getEmail()));
    }
}
