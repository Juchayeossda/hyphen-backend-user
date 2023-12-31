package com.get.hyphenbackenduser.global.util;

import com.get.hyphenbackenduser.global.config.webClient.WebClientConfig;
import com.get.hyphenbackenduser.global.lib.webClient.dto.response.ImageUploadResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;

@Component
@RequiredArgsConstructor
@Getter
public class WebClientUtil {

    private final WebClientConfig webClientConfig;

    @Value(value = "${webClient.servers.imageServer.path}")
    private String imageServerPath;


    public ImageUploadResponse imageUpload(MultipartBodyBuilder imageBuilder) {
        return webClientConfig.webClient().method(HttpMethod.POST)
                .uri(imageServerPath + "/api/siss/storages/images/image")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(imageBuilder.build()))
                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(InternalServerException.EXCEPTION))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(InternalServerException.EXCEPTION))
                .bodyToMono(ImageUploadResponse.class)
                .block();
    }
}
