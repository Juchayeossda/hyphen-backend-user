package com.get.hyphenbackenduser.domain.inquiry.service;

import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.request.AnswerRequest;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response.AnswerResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response.GetAllInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response.GetInquiryResponse;

public interface InquiryAdminService {

    GetAllInquiryResponse getAllInquiry();
    public GetInquiryResponse getInquiry(String id);
    AnswerResponse answering(String id, AnswerRequest answerRequest);
}
