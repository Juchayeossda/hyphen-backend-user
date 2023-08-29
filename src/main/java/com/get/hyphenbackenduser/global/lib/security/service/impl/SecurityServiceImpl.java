package com.get.hyphenbackenduser.global.lib.security.service.impl;

import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.global.lib.security.principal.UserPrincipal;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    public User getAuthUserInfo() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getUser();
    }

    public User getAuthUserInfo(Authentication authenticate) {
        UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();
        return userPrincipal.getUser();
    }
}
