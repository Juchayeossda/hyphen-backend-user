package com.get.hyphenbackenduser.global.lib.security.service;

import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import org.springframework.security.core.Authentication;

public interface SecurityService {

    User getAuthUserInfo();
    User getAuthUserInfo(Authentication authenticate);
}
