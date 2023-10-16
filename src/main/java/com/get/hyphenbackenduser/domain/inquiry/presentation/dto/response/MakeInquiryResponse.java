package com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "MakeInquiryResponse: 문의 응답 Dto", description = "문의 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakeInquiryResponse {

    @Schema(description = "상태")
    private String status;
}
