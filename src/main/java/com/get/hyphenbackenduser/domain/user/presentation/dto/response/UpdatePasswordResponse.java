package com.get.hyphenbackenduser.domain.user.presentation.dto.response;

import com.get.hyphenbackenduser.global.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Schema(title = "UpdatePasswordResponse: 패스워드 수정 응답 Dto", description = "내 패스워드 수정 응답 객체")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePasswordResponse {

    @Schema(description = "상태")
    private Status status;
}
