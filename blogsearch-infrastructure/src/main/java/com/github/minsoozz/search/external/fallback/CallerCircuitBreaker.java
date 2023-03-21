package com.github.minsoozz.search.external.fallback;

import com.github.minsoozz.search.external.dto.common.ApiResponseDto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface CallerCircuitBreaker {

    ApiResponseDto defaultFallback(final String query, final Integer page, final String sort);
}
