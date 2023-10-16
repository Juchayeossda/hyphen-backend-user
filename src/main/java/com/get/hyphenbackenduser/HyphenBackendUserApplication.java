package com.get.hyphenbackenduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HyphenBackendUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyphenBackendUserApplication.class, args);
    }
}
