package com.get.hyphenbackenduser.domain.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Schema(title = "RenameRequest: 이름 수정 요청 Dto", description = "내 프로필 이름 수정 요청 객체")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RenameRequest {

    @Schema(description = "새로운 이름")
    @NotBlank(message = "name can not be blank.")
    @Size(min=2, max=12, message = "name must be at least 2 characters long and not more than 12 characters long.")
    private String newName;
}
