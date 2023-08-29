package com.get.hyphenbackenduser.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "file.image")
public class ImageStorageProperties {

    private String path;
}
