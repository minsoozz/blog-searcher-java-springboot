package com.github.minsoozz.search.spec;

import java.time.LocalDateTime;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogApiPost(
        String blogName,
        String contents,
        LocalDateTime dateTime,
        String thumbnail,
        String title,
        String url) {
}
