package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import com.get.hyphenbackenduser.domain.user.enums.UserRole;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "WithdrawalUserResponse: 회원 탈퇴 응답 Dto", description = "회원 탈퇴 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithdrawalUserResponse {

    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "사용자 상태")
    private UserStatus userStatus;
    @Schema(description = "사용자 권한")
    private UserRole userRole;
}