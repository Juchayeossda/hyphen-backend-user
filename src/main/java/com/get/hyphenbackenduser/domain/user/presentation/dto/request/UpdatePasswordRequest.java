package com.get.hyphenbackenduser.domain.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Schema(title = "UpdatePasswordRequest: 패스워드 수정 요청 Dto", description = "내 패스워드 수정 요청 객체")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePasswordRequest {

    @Schema(description = "원래 패스워드")
    @NotBlank(message = "password can not be blank.")
    @Size(min=8, max=24, message = "password must be at least 8 characters long and not more than 24 characters long.")
    private String originPassword;

    @Schema(description = "새로운 패스워드")
    @NotBlank(message = "password can not be blank.")
    @Size(min=8, max=24, message = "password must be at least 8 characters long and not more than 24 characters long.")
    private String newPassword;
}
