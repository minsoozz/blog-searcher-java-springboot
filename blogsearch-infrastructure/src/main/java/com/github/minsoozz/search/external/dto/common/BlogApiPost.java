package com.github.minsoozz.search.external.dto.common;

import com.github.minsoozz.search.external.dto.fallback.FallbackApiResponseDto.Item;
import com.github.minsoozz.search.external.dto.kakao.KakaoApiResponseDto.Document;
import com.github.minsoozz.search.utils.LocalDateTimeUtils;
import java.time.LocalDateTime;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogApiPost(String blogName,
                          String contents,
                          LocalDateTime dateTime,
                          String thumbnail,
                          String title,
                          String url) {

    public static BlogApiPost from(final Document document) {
        return new BlogApiPost(
            document.blogname(),
            document.contents(),
            document.datetime(),
            document.thumbnail(),
            document.title(),
            document.url());
    }

    public static BlogApiPost from(final Item item) {
        return new BlogApiPost(
            item.bloggerName(),
            item.description(),
            LocalDateTimeUtils.stringToLocalDateTime(item.postDate()),
            null,
            item.title(),
            item.bloggerName()
        );
    }
}
