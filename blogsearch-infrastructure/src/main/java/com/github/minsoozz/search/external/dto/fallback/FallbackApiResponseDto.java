package com.github.minsoozz.search.external.dto.fallback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.minsoozz.search.external.dto.common.ApiResponseDto;
import com.github.minsoozz.search.external.dto.common.BlogApiMetadata;
import com.github.minsoozz.search.external.dto.common.BlogApiPost;
import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record FallbackApiResponseDto(@JsonProperty("lastBuildDate") String lastBuildDate,
                                     @JsonProperty("total") int total,
                                     @JsonProperty("start") int start,
                                     @JsonProperty("display") int display,
                                     @JsonProperty("items") List<Item> items) implements ApiResponseDto {

    public record Item(
        @JsonProperty("title") String title,
        @JsonProperty("link") String link,
        @JsonProperty("description") String description,
        @JsonProperty("bloggername") String bloggerName,
        @JsonProperty("bloggerlink") String bloggerLink,
        @JsonProperty("postdate") String postDate
    ) {

    }

    @Override
    public List<BlogApiPost> getContents() {
        return this.items
            .stream()
            .map(BlogApiPost::from)
            .toList();
    }

    @Override
    public BlogApiMetadata getMeta() {
        return BlogApiMetadata.of(null, this.total, this.total);
    }

}