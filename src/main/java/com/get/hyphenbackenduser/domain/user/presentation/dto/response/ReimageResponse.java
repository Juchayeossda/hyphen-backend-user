package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "ReimageResponse: 이미지 수정 응답 Dto", description = "내 프로필 이미지 수정 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReimageResponse {

    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "이미지 아이디")
    private Long imageId;
    @Schema(description = "이미지 이름")
    private String imageName;
    @Schema(description = "이미지 경로")
    private String imagePath;
}
