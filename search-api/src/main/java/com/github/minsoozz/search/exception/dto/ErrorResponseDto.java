package com.github.minsoozz.search.exception.dto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record ErrorResponseDto(
        int code,
        String message
) {
}
