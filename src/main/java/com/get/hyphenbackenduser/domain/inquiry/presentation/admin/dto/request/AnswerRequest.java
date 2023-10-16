package com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(title = "AnswerRequest: 문의 답변 요청 Dto", description = "서비스 문의 답변 요청 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequest {

    @Schema(description = "답변")
    @NotBlank(message = "answer can not be blank.")
    private String answer;
}
