package com.get.hyphenbackenduser.domain.inquiry.service;

import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.request.MakeInquiryRequest;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.GetInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.GetMyInquirysResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.MakeInquiryResponse;

public interface InquiryService {

    MakeInquiryResponse makeInquiry(MakeInquiryRequest makeInquiryRequest);
    GetInquiryResponse getInquiry(String id);
    GetMyInquirysResponse getMyInquirys();
}
