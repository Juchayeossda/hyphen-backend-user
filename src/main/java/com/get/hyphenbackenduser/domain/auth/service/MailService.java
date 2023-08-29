package com.get.hyphenbackenduser.domain.auth.service;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.MailResponse;

public interface MailService {

    MailResponse sendMessage(String to)throws Exception;
}
