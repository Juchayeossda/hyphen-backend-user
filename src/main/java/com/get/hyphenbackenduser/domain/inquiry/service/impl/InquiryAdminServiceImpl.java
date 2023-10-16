package com.get.hyphenbackenduser.domain.inquiry.service.impl;

import com.get.hyphenbackenduser.domain.inquiry.domain.entity.Inquiry;
import com.get.hyphenbackenduser.domain.inquiry.domain.repository.InquiryRepository;
import com.get.hyphenbackenduser.domain.inquiry.enums.InquiryStatus;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.request.AnswerRequest;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response.AnswerResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response.GetAllInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.admin.dto.response.GetInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.service.InquiryAdminService;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryAdminServiceImpl implements InquiryAdminService {

    private final InquiryRepository inquiryRepository;
    private final SecurityService securityService;

    @Transactional
    public GetAllInquiryResponse getAllInquiry() {
        User user = securityService.getAuthUserInfo();
        if (user.getUserRole().equals("ADMIN")) {
            return GetAllInquiryResponse.builder().build();
        }
        return GetAllInquiryResponse.builder()
                .inquiries(inquiryRepository.findAll())
                .build();
    }

    @Transactional
    public GetInquiryResponse getInquiry(String id) {
        User user = securityService.getAuthUserInfo();
        if (user.getUserRole().equals("ADMIN")) {
            return GetInquiryResponse.builder().build();
        }
        return GetInquiryResponse.builder()
                .inquiry(inquiryRepository.findById(Long.valueOf(id)).get())
                .build();
    }

    @Transactional
    public AnswerResponse answering(String id, AnswerRequest answerRequest) {
        User user = securityService.getAuthUserInfo();
        if (user.getUserRole().equals("ADMIN")) {
            Inquiry inquiry = inquiryRepository.findById(Long.valueOf(id)).get();
            inquiry.setAnswer(answerRequest.getAnswer());
            inquiry.setInquiryStatus(InquiryStatus.ANSWERS_PRESENT);
            inquiryRepository.save(inquiry);
            return AnswerResponse.builder()
                    .uid(inquiry.getUid())
                    .inquiryStatus(inquiry.getInquiryStatus())
                    .status("답변 성공")
                    .build();
        }
        return AnswerResponse.builder()
                .status("답변 실패")
                .build();
    }
}

