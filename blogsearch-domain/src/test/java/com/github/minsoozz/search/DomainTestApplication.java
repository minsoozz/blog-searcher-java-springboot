package com.github.minsoozz.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.github.minsoozz")
public class DomainTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomainTestApplication.class);
    }
}
