package com.github.minsoozz.search.external.fallback.impl;

import com.github.minsoozz.search.exception.ApiBadRequestException;
import com.github.minsoozz.search.external.enums.ApiQueryParameterKey;
import com.github.minsoozz.search.external.fallback.CallerCircuitBreaker;
import com.github.minsoozz.search.external.properties.ExternalApiProperties;
import com.github.minsoozz.search.spec.ApiResponseDto;
import com.github.minsoozz.search.utils.HttpHeaderUtils;
import com.github.minsoozz.search.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CallerCircuitBreakerImpl implements CallerCircuitBreaker {

    private static final Logger logger = LoggerFactory.getLogger(CallerCircuitBreakerImpl.class);
    private static final String NAVER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    private final RestTemplate restTemplate;
    private final ExternalApiProperties externalApiProperties;
    private final ObjectMapperUtils objectMapperUtils;

    public CallerCircuitBreakerImpl(@Qualifier("naverRestTemplate") final RestTemplate restTemplate,
                                    final ExternalApiProperties externalApiProperties,
                                    final ObjectMapperUtils objectMapperUtils) {
        this.restTemplate = restTemplate;
        this.externalApiProperties = externalApiProperties;
        this.objectMapperUtils = objectMapperUtils;
    }

    @Override
    public ApiResponseDto defaultFallback(String query, final Integer page, final String sort) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(externalApiProperties.naver().apiUri())
                .queryParam(ApiQueryParameterKey.NAVER.getQuery(), query)
                .queryParam(ApiQueryParameterKey.NAVER.getPage(), page)
                .queryParam(ApiQueryParameterKey.NAVER.getSort(), convertSortOrderToFallbackSortString(sort))
                .build();

        HttpHeaders headers = HttpHeaderUtils.createJsonMediaType();
        headers.set(NAVER_CLIENT_ID, externalApiProperties.naver().clientId());
        headers.set(NAVER_CLIENT_SECRET, externalApiProperties.naver().clientSecret());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uriComponents.encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            return null;
        } catch (HttpClientErrorException e) {
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            logger.error("fallback : {}", stackTraceElements[0]);
            throw new ApiBadRequestException(e.getMessage());
        }
    }

    private String convertSortOrderToFallbackSortString(String sort) {
        return switch (sort) {
            case "recency" -> "date";
            default -> "sim";
        };
    }
}
