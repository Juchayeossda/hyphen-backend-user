package com.get.hyphenbackenduser.global.lib.oauth2.handler;

import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.domain.user.domain.repository.UserRepository;
import com.get.hyphenbackenduser.domain.user.enums.UserRole;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import com.get.hyphenbackenduser.global.lib.jwt.JwtProvider;
import com.get.hyphenbackenduser.global.lib.oauth2.user.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private String tokenHeader = "Authorization";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 성공.");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            String name = oAuth2User.getName();
            //TODO: occurs exception: NoSuchElementException: No value present
            //      그런데 최초 가입의 경우에는...?
            User user = userRepository.findByUid(oAuth2User.getName()).get();
            if(oAuth2User.getRole() == UserRole.GUEST) {
                user.setUserRole(UserRole.MEMBER);
                user.setUserStatus(UserStatus.ACTIVE);
                userRepository.save(user);
                String accessToken = jwtProvider.generateToken(oAuth2User);
                String refreshToken = jwtProvider.generateRefreshToken(oAuth2User);
                response.addHeader(tokenHeader, "Bearer " + accessToken);
                response.addHeader(tokenHeader, "Bearer " + refreshToken);
                log.info("OAuth2 회원가입 성공.");
            } else {
                loginSuccess(response, oAuth2User);
                user.setUserStatus(UserStatus.ACTIVE);
                userRepository.save(user);
                log.info("OAuth2 Login 성공.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtProvider.generateToken(oAuth2User);
        String refreshToken = jwtProvider.generateRefreshToken(oAuth2User);
        response.addHeader(tokenHeader, "Bearer " + accessToken);
        response.addHeader(tokenHeader, "Bearer " + refreshToken);
    }
}
