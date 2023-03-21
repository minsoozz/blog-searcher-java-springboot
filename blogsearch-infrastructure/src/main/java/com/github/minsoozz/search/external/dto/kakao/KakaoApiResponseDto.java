package com.github.minsoozz.search.external.dto.kakao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.minsoozz.search.external.dto.common.ApiResponseDto;
import com.github.minsoozz.search.external.dto.common.BlogApiMetadata;
import com.github.minsoozz.search.external.dto.common.BlogApiPost;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record KakaoApiResponseDto(List<Document> documents, Meta meta) implements ApiResponseDto {

    @Override
    public List<BlogApiPost> getContents() {
        return this.documents
            .stream()
            .map(BlogApiPost::from)
            .toList();
    }

    @Override
    public BlogApiMetadata getMeta() {
        return BlogApiMetadata.of(this.meta.isEnd, this.meta.pageableCount, this.meta.totalCount);
    }

    public record Document(String blogname,
                           String contents,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Seoul")
                           LocalDateTime datetime,
                           String thumbnail,
                           String title,
                           String url) {

    }

    public record Meta(@JsonProperty("is_end") boolean isEnd,
                       @JsonProperty("pageable_count") int pageableCount,
                       @JsonProperty("total_count") int totalCount) {

    }
}
