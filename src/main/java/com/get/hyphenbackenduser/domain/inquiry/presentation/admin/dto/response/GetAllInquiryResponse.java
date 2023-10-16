package com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response;

import com.get.hyphenbackenduser.domain.inquiry.domain.entity.Inquiry;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(title = "getAllInquiryResponse: 문의 조회 응답 Dto", description = "서비스 문의 조회 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetAllInquiryResponse {

    @Schema(description = "문의 정보 리스트")
    private List<Inquiry> inquiries;
}