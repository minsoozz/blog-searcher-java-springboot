package com.github.minsoozz.search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = "com.github.minsoozz.search.persistence.repository"
)
public class JpaConfiguration {

}
