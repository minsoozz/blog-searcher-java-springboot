package com.github.minsoozz.search.lock;

import com.github.minsoozz.search.exception.DistributedLockAcquisitionFailedException;
import com.github.minsoozz.search.exception.DistributedLockingException;
import java.util.function.Supplier;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface DistributedLock {

    <T> T acquire(final String lockName, final Supplier<T> run)
        throws DistributedLockAcquisitionFailedException, DistributedLockingException;
}
