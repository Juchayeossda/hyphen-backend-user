package com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response;

import com.get.hyphenbackenduser.domain.inquiry.domain.entity.Inquiry;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "GetInquiryResponse: 문의 상세 조회 응답 Dto", description = "서비스 문의 상세 정보 조회 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetInquiryResponse {

    @Schema(description = "문의 정보")
    private Inquiry inquiry;
}