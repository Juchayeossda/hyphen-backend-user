package com.get.hyphenbackendinquiry.domain.inquiry.presentation.dto.request;

import com.get.hyphenbackendinquiry.domain.inquiry.domain.Inquiry;
import com.get.hyphenbackendinquiry.domain.inquiry.enums.InquiryStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakeInquiryRequest {

    private String uid;
    private String title;
    private String content;

    public Inquiry toEntity(){
        return Inquiry.builder()
                .uid(uid)
                .inquiryStatus(InquiryStatus.NO_ANSWER)
                .title(title)
                .content(content)
                .build();
    }
}
