package com.get.hyphenbackenduser.domain.mail.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(title = "MailResponse: 메일 인증 요청 Dto", description = "메일 인증키 전송 요청 객체")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailRequest {

    @Schema(description = "사용자 이메일")
    @NotNull(message = "email can not be null.")
    @Email(message = "email format is not correct.")
    private String email;
}
