package com.github.minsoozz.search.exception;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class DistributedLockAcquisitionFailedException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "락을 획득할 수 없습니다 [lockName=%s]";

    public DistributedLockAcquisitionFailedException(String message) {
        super(String.format(MESSAGE_FORMAT, message));
    }
}
