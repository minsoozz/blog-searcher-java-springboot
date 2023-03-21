package com.github.minsoozz.search.utils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class HttpHeaderUtils {

    private HttpHeaderUtils() {

    }

    public static HttpHeaders createJsonMediaType() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
