package com.github.minsoozz.search.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Component
public class ObjectMapperUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectMapperUtils.class);
    private final ObjectMapper objectMapper;

    public ObjectMapperUtils(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T jsonToObject(String json, Class<T> clazz) {
        if (ObjectUtils.isEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            logger.error("IOException : {}", stackTraceElements[0]);
        }
        return null;
    }
}

