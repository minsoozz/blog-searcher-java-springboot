package com.github.minsoozz.search.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Configuration
public class RedissonConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    @DependsOn("embeddedRedisConfiguration")
    public RedissonClient redissonClient() {
        final String address = "redis://" + redisHost + ":" + redisPort;
        Config config = new Config();
        config.useSingleServer()
            .setAddress(address);
        return Redisson.create(config);
    }
}
