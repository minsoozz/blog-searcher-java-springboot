package com.github.minsoozz.search.exception.dto;

import org.springframework.http.HttpStatus;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record ErrorResponseDto(
    int code,
    String message
) {
}
