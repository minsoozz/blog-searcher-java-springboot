package com.github.minsoozz.search.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author minsoozz
 * @date 2023.07.01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    String key(); // 락을 걸 key

    TimeUnit timeUnit() default TimeUnit.SECONDS; // 락 시간 단위

    long waitTime() default 5L; // 락을 획득하기 위한 최대 대기 시간

    long leaseTime() default 3L; // 락을 획득한 이후 시간이 지나면 락을 해제한다
}
