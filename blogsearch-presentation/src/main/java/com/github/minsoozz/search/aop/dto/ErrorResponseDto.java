package com.github.minsoozz.search.aop.dto;

import org.springframework.http.HttpStatus;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record ErrorResponseDto(
    int code,
    String message
) {

    public static ErrorResponseDto toErrorResponseDto(final HttpStatus httpStatus, final String message) {
        return new ErrorResponseDto(httpStatus.value(), message);
    }
}
