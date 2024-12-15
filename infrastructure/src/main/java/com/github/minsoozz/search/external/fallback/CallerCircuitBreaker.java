package com.github.minsoozz.search.external.fallback;


import com.github.minsoozz.search.spec.ApiResponseDto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface CallerCircuitBreaker {

    ApiResponseDto defaultFallback(String query, final Integer page, final String sort);
}
