package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import com.get.hyphenbackenduser.global.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "DeleteProfileImageResponse: 내 프로필 삭제 응답 Dto", description = "내 프로필 이미지 삭제 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteProfileImageResponse {

    @Schema(description = "상태")
    private Status status;
}
