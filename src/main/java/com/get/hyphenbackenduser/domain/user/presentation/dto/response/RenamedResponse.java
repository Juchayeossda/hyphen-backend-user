package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(title = "RenamedResponse: 이름 수정 응답 Dto", description = "내 프로필 이름 수정 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RenamedResponse {

    @Schema(description = "사용자 아이디")
    private String uid;
    @Schema(description = "수정 전 이름")
    private String originName;
    @Schema(description = "수정 후 이름")
    private String newName;
}
