package com.github.minsoozz.search.lock.service;

import com.github.minsoozz.search.exception.DistributedLockAcquisitionFailedException;
import com.github.minsoozz.search.exception.DistributedLockingException;
import com.github.minsoozz.search.lock.DistributedLock;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class RedisDistributedLockService implements DistributedLock {

    private final RedissonClient redissonClient;

    public RedisDistributedLockService(final RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public <T> T acquire(final String lockName, final Supplier<T> run)
        throws DistributedLockAcquisitionFailedException, DistributedLockingException {
        final RLock lock = redissonClient.getLock(lockName);
        try {
            boolean acquired = lock.tryLock(3, 2, TimeUnit.SECONDS);
            if (!acquired) {
                throw new DistributedLockAcquisitionFailedException(lockName);
            }
            return run.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DistributedLockingException(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}