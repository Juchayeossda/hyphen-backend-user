package com.get.hyphenbackenduser.global.lib.oauth2.user.global;

import java.util.Map;

public abstract class OAuth2User {

    protected Map<String, Object> attributes;

    public abstract String getId(); //소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"

    public abstract String getName();
//    public abstract String getEmail();

    public abstract String getImageUrl();

    public OAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}