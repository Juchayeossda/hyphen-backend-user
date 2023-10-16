package com.get.hyphenbackenduser.domain.mail.service;


import com.get.hyphenbackenduser.domain.mail.presentation.dto.response.MailResponse;

public interface MailService {

    MailResponse sendMessage(String to)throws Exception;
}
