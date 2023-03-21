package com.github.minsoozz.search.exception;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class BlogSearchException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "블로그 조회 중 오류가 발생하였습니다 [message=%s]";

    public BlogSearchException(final String message) {
        super(String.format(MESSAGE_FORMAT, message));
    }
}
