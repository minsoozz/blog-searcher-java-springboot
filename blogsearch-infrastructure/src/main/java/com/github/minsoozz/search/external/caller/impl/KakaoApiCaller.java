package com.github.minsoozz.search.external.caller.impl;

import com.github.minsoozz.search.exception.ApiBadRequestException;
import com.github.minsoozz.search.external.caller.BlogApiCaller;
import com.github.minsoozz.search.external.dto.common.ApiResponseDto;
import com.github.minsoozz.search.external.dto.kakao.KakaoApiResponseDto;
import com.github.minsoozz.search.external.enums.ApiQueryParameterKey;
import com.github.minsoozz.search.external.fallback.CallerCircuitBreaker;
import com.github.minsoozz.search.external.properties.ExternalApiProperties;
import com.github.minsoozz.search.utils.HttpHeaderUtils;
import com.github.minsoozz.search.utils.ObjectMapperUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class KakaoApiCaller implements BlogApiCaller {

    private static final Logger logger = LoggerFactory.getLogger(KakaoApiCaller.class);
    private static final String KAKAO_AUTHORIZATION_KEY_PREFIX = "KakaoAK ";

    private final RestTemplate restTemplate;
    private final ExternalApiProperties externalApiProperties;
    private final CallerCircuitBreaker callerCircuitBreaker;
    private final ObjectMapperUtils objectMapperUtils;

    public KakaoApiCaller(@Qualifier("kakaoRestTemplate") final RestTemplate restTemplate,
        final ExternalApiProperties externalApiProperties,
        final CallerCircuitBreaker callerCircuitBreaker,
        final ObjectMapperUtils objectMapperUtils) {
        this.restTemplate = restTemplate;
        this.externalApiProperties = externalApiProperties;
        this.callerCircuitBreaker = callerCircuitBreaker;
        this.objectMapperUtils = objectMapperUtils;
    }

    @CircuitBreaker(name = "caller", fallbackMethod = "fallback")
    public ApiResponseDto searchBlogs(final String query, final Integer page, final String sort) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(externalApiProperties.kakao().apiUri())
            .queryParam(ApiQueryParameterKey.KAKAO.getQuery(), query)
            .queryParam(ApiQueryParameterKey.KAKAO.getPage(), page)
            .queryParam(ApiQueryParameterKey.KAKAO.getSort(), sort)
            .build();

        HttpHeaders headers = HttpHeaderUtils.createJsonMediaType();
        headers.set(HttpHeaders.AUTHORIZATION, KAKAO_AUTHORIZATION_KEY_PREFIX + externalApiProperties.kakao().apiKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                uriComponents.encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
            return objectMapperUtils.jsonToObject(response.getBody(), KakaoApiResponseDto.class);
        } catch (HttpClientErrorException e) {
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            logger.error("HttpClientErrorException : {}", stackTraceElements[0]);
            throw new ApiBadRequestException(e.getMessage());
        }
    }

    public ApiResponseDto fallback(final String query, final Integer page, final String sort, Throwable t) {
        logger.error("CircuitBreaker fallback start : {}", t.getMessage());
        return callerCircuitBreaker.defaultFallback(query, page, sort);
    }
}
