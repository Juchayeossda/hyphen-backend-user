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

    public static void main(String[] args) {
        SpringApplication.run(HyphenBackendUserApplication.class, args);
    }
}
