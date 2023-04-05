package com.github.minsoozz.search.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate kakaoRestTemplate() {
        return new RestTemplateBuilder()
            .requestFactory(this::httpRequestFactory)
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(3))
            .interceptors()
            .build();
    }

    @Bean
    public RestTemplate naverRestTemplate() {
        return new RestTemplateBuilder()
            .requestFactory(this::httpRequestFactory)
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(3))
            .build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(5)
            .setConnectionTimeToLive(30, TimeUnit.SECONDS)
            .evictIdleConnections(30, TimeUnit.SECONDS)
            .evictExpiredConnections()
            .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(closeableHttpClient);
        return factory;
    }
}
