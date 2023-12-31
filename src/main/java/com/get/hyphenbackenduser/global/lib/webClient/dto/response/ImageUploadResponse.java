package com.get.hyphenbackenduser.global.lib.webClient.dto.response;

import com.get.hyphenbackenduser.global.lib.webClient.dto.Data;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUploadResponse {

    private int code;
    private String message;
    private Data data;
}
