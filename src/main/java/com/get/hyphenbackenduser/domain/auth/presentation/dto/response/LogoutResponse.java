package com.get.hyphenbackenduser.domain.auth.presentation.dto.response;

import com.get.hyphenbackenduser.domain.user.enums.UserRole;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import com.get.hyphenbackenduser.global.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "RegisterResponse: 회원가입 응답 Dto", description = "회원가입 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogoutResponse {

    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "사용자 상태")
    private UserStatus userStatus;
    @Schema(description = "상태")
    private Status status;
}
