package com.get.hyphenbackenduser.global.lib.security.service.impl;

import com.get.hyphenbackenduser.domain.user.exception.NotFoundUserException;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.domain.user.domain.repository.UserRepository;
import com.get.hyphenbackenduser.global.lib.security.principal.UserPrincipal;
import com.get.hyphenbackenduser.global.lib.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUid(username)
                .orElseThrow(() -> NotFoundUserException.EXCEPTION);
        return UserPrincipal.create(user);
    }
}
