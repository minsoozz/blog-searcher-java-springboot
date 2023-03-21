package com.github.minsoozz.search.exception;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class ApiBadRequestException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "올바르지 않은 요청입니다 [message=%s]";

    public ApiBadRequestException(final String message) {
        super(String.format(MESSAGE_FORMAT, message));
    }
}
