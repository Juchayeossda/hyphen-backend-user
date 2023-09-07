package com.get.hyphenbackenduser;

import com.get.hyphenbackenduser.global.properties.ImageStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(ImageStorageProperties.class)
public class HyphenBackendUserApplication {
    // TODO: 나르샤 (문의 서버, 그림 감상 서버, OAuth2, 유저서버 마무리, 디자인)
    public static void main(String[] args) {
        SpringApplication.run(HyphenBackendUserApplication.class, args);
    }
}
