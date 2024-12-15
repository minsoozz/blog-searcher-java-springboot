package com.github.minsoozz.search.exception;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class FailedToAcquireLockException extends BusinessException {

    private static final String MESSAGE_FORMAT = "현재 시스템에 문제가 발생하여 일시적으로 이용이 불가능합니다. 이 문제는 빠른 시일 내에 해결될 예정입니다.";

    public FailedToAcquireLockException(Throwable throwable) {
        super(MESSAGE_FORMAT, throwable);
    }
}
