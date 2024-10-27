package com.github.minsoozz.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@SpringBootApplication
@ConfigurationPropertiesScan("com.github.minsoozz")
public class BlogSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApplication.class);
    }
}
