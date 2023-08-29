package com.get.hyphenbackenduser.domain.auth.service.impl;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.response.MailResponse;
import com.get.hyphenbackenduser.domain.auth.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
@Configuration
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Autowired
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    public String authKey;
    @Value("${AdminMail.id}")
    private String senderMail;
    @Value("${AdminMail.name}")
    private String senderName;

    private MimeMessage createMessage(String emailRecipient) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, emailRecipient);
        message.setSubject("[인증번호:"+ authKey +"] 하이픈 이메일 인증을 진행해 주세요.\n");
        message.setText(/*mailForm.getMailForm(authNumber)*/setContext(authKey), "utf-8", "html");
        message.setFrom(new InternetAddress(senderMail,senderName));
        return message;
    }

    private String setContext(String authKey) {
        Context context = new Context();
        context.setVariable("authKey", authKey);
        return templateEngine.process("mail", context);
    }

    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            switch (r.nextInt(3)) {
                case 0: key.append((char) ((int) (r.nextInt(26)) + 97)); break; //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                case 1: key.append((char) ((int) (r.nextInt(26)) + 65)); break; //  A~Z
                case 2: key.append((r.nextInt(10))); break; // 0~9
            }
        }
        return key.toString();
    }

    @Override
    public MailResponse sendMessage(String emailRecipient) throws Exception {
        authKey = createKey();
        MimeMessage message = createMessage(emailRecipient);
        try{
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return MailResponse.builder()
                .sendEmail(senderMail)
                .recvEmail(emailRecipient)
                .authNumber(authKey)
                .build();
    }
}
