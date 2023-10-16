package com.get.hyphenbackenduser.domain.inquiry.service.impl;

import com.get.hyphenbackenduser.domain.inquiry.domain.repository.InquiryRepository;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.request.MakeInquiryRequest;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.GetInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.GetMyInquirysResponse;
import com.get.hyphenbackenduser.domain.inquiry.presentation.dto.response.MakeInquiryResponse;
import com.get.hyphenbackenduser.domain.inquiry.service.InquiryService;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final SecurityService securityService;

    @Transactional
    public MakeInquiryResponse makeInquiry(MakeInquiryRequest makeInquiryRequest) {
        User user = securityService.getAuthUserInfo();
        if (inquiryRepository.save(makeInquiryRequest.toEntity(user.getUid())) != null) {
            return MakeInquiryResponse.builder()
                    .status("문의 성공")
                    .build();
        } else {
            return MakeInquiryResponse.builder()
                    .status("문의 실패")
                    .build();
        }
    }

    @Transactional
    public GetInquiryResponse getInquiry(String id) {
        return GetInquiryResponse.builder()
                .inquiry(inquiryRepository.findById(Long.valueOf(id)).get())
                .build();
    }

    @Transactional
    public GetMyInquirysResponse getMyInquirys() {
        User user = securityService.getAuthUserInfo();
        return GetMyInquirysResponse.builder()
                .inquiries(inquiryRepository.findAllByUid(user.getUid()).get())
                .build();
    }
}

