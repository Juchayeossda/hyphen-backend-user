package com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response;

import com.get.hyphenbackenduser.domain.inquiry.domain.entity.Inquiry;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(title = "GetMyInquirysResponse: 내 문의 조회 응답 Dto", description = "내 서비스 문의 조회 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMyInquirysResponse {

    @Schema(description = "문의 정보 리스트")
    private List<Inquiry> inquiries;
}
