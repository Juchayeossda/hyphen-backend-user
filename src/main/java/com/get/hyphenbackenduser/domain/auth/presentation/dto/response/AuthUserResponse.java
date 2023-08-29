package com.get.hyphenbackenduser.domain.auth.presentation.dto.response;

import com.get.hyphenbackenduser.domain.user.enums.UserRole;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "AuthUserResponse: 인가 응답 Dto", description = "인가 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthUserResponse {

    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "사용자 상태", nullable = false)
    private UserStatus userStatus;
    @Schema(description = "사용자 권한", nullable = false)
    private UserRole userRole;
}
