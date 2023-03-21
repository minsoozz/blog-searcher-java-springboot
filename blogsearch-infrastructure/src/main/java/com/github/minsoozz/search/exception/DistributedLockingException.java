package com.github.minsoozz.search.exception;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class DistributedLockingException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "락을 처리하는 과정에서 예외가 발생했습니다 [message=%s]";

    public DistributedLockingException(final String message) {
        super(String.format(MESSAGE_FORMAT, message));
    }
}
