package com.get.hyphenbackenduser.domain.auth.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private String id;
    private String uid;
    private String name;
    private String email;
    private String password;
}
