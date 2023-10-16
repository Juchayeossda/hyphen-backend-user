package com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response;

import com.get.hyphenbackenduser.domain.inquiry.enums.InquiryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "AnswerResponse: 문의 답변 응답 Dto", description = "서비스 문의 답변 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerResponse {

    @Schema(description = "문의자 아이디")
    private String uid;

    @Schema(description = "문의 상태")
    private InquiryStatus inquiryStatus;

    @Schema(description = "상태")
    private String status;
}
