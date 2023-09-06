package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "GetProfileImageResponse: 내 프로필 조회 응답 Dto", description = "내 프로필 이미지 조회 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetProfileImageResponse {

    @Schema(description = "이미지 주소")
    private String imageUrl;
}
