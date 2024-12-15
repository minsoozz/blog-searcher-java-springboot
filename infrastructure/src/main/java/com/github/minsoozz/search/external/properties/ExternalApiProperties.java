package com.github.minsoozz.search.external.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@ConstructorBinding
@ConfigurationProperties("blog.search")
public record ExternalApiProperties(Kakao kakao, Naver naver) {

    public record Kakao(String apiUri, String apiKey) {

    }

    public record Naver(String apiUri, String clientId, String clientSecret) {

    }
}
