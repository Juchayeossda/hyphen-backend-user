package com.get.hyphenbackenduser.domain.mail.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "MailResponse: 메일 인증 응답 Dto", description = "메일 인증키 전송 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailResponse {

    @Schema(description = "인증키 전송자 이메일")
    private String sendEmail;
    @Schema(description = "인증키 송신자 이메일")
    private String recvEmail;
    @Schema(description = "메일 인증키")
    private String authNumber;
}
