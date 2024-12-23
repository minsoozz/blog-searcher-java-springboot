package com.github.minsoozz.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Configuration
@ConditionalOnProperty(name = "redis.docker.enabled", havingValue = "true")
public class EmbeddedRedisConfiguration {

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.maxHeap}")
    private String maxHeap;

    private RedisServer redisServer;

    @PostConstruct
    public void start() {
        final String configLine = "maxmemory " + maxHeap;
        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting(configLine)
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stop() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
