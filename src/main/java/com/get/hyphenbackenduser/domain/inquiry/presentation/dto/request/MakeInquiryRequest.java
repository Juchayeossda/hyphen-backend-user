package com.get.hyphenbackenduser.domain.inquiry.presentation.dto.request;

import com.get.hyphenbackenduser.domain.inquiry.domain.entity.Inquiry;
import com.get.hyphenbackenduser.domain.inquiry.enums.InquiryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(title = "MakeInquiryRequest: 문의 요청 Dto", description = "서비스 문의 요청 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakeInquiryRequest {

    @Schema(description = "문의 제목")
    @NotBlank(message = "title can not be blank.")
    private String title;

    @Schema(description = "문의 내용")
    @NotBlank(message = "content can not be blank.")
    private String content;

    public Inquiry toEntity(String uid){
        return Inquiry.builder()
                .uid(uid)
                .inquiryStatus(InquiryStatus.NO_ANSWER)
                .title(title)
                .content(content)
                .build();
    }
}
