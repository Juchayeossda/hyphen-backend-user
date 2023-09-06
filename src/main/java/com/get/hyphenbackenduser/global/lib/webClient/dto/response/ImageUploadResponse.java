package com.get.hyphenbackenduser.global.lib.webClient.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUploadResponse {

    private String ident;
    private String status;
}
