package com.github.minsoozz.search.aop;

import com.github.minsoozz.search.exception.DistributedLockAcquisitionFailedException;
import com.github.minsoozz.search.exception.DistributedLockingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author minsoozz
 * @date 2023.07.01
 */
@Aspect
@Order(1)
@Component
public class DistributedLockAspect {

    static final Logger logger = LoggerFactory.getLogger(DistributedLockAspect.class);
    private final RedissonClient redissonClient;

    public DistributedLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(com.github.minsoozz.search.annotations.DistributedLock)")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        final String lockName = joinPoint.getSignature().getName();
        final RLock lock = redissonClient.getLock(lockName);
        boolean acquired = false;
        try {
            acquired = lock.tryLock(5, 3, TimeUnit.SECONDS);
            if (!acquired) {
                throw new DistributedLockAcquisitionFailedException(lockName);
            }
            return joinPoint.proceed();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DistributedLockingException(e.getMessage());
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
    }
}
