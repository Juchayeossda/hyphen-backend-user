package com.get.hyphenbackenduser.domain.auth.presentation.dto.request;

import com.get.hyphenbackenduser.domain.auth.presentation.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(title = "RegisterRequest: 회원가입 요청 Dto", description = "회원가입 요청 객체")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterRequest {

    @Schema(description = "사용자 아이디")
    @NotBlank(message = "uid can not be blank.")
    @Size(min=8, max=16, message = "uid must be at least 8 characters long and not more than 16 characters long.")
    private String uid;

    @Schema(description = "사용자 이름")
    @NotBlank(message = "name can not be blank.")
    @Size(min=2, max=12, message = "name must be at least 2 characters long and not more than 12 characters long.")
    private String name;

    @Schema(description = "사용자 이메일")
    @NotBlank(message = "email can not be blank.")
    @Email(message = "email format is not correct.")
    private String email;

    @Schema(description = "사용자 패스워드")
    @NotBlank(message = "password can not be blank.")
    @Size(min=8, max=24, message = "password must be at least 8 characters long and not more than 24 characters long.")
    private String password;

    public UserDTO toUserDTO(){
        return UserDTO.builder()
                .uid(uid)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
