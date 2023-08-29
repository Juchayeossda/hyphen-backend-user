package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import com.get.hyphenbackenduser.domain.user.enums.SocialType;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "MyInfoResponse: 내 정보 조회 응답 Dto", description = "내 정보 조회 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyInfoResponse {

    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "사용자 이름")
    private String name;
    @Schema(description = "사용자 이메일")
    private String email;
    @Schema(description = "사용자 상태")
    private UserStatus userStatus;
    @Schema(description = "OAuth 타입")
    private SocialType socialType;
    @Schema(description = "OAuth 아이디")
    private String socialId;
}
