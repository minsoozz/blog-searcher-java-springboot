package com.github.minsoozz.search.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class LocalDateTimeUtils {

    private LocalDateTimeUtils() {

    }

    public static LocalDateTime stringToLocalDateTime(final String postDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(postDate, formatter).atStartOfDay();
    }
}
