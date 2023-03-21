package com.github.minsoozz.search.domain.blog;

import com.github.minsoozz.search.external.dto.common.BlogApiMetadata;
import com.github.minsoozz.search.external.dto.common.BlogApiPost;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogSearch(List<Document> documents, Meta meta) {

    public static BlogSearch of(List<Document> documents, Meta meta) {
        return new BlogSearch(documents, meta);
    }

    public record Document(String blogname,
                           String contents,
                           LocalDateTime datetime,
                           String thumbnail,
                           String title,
                           String url) {

        public static Document from(final BlogApiPost document) {
            return new Document(
                document.blogName(),
                document.contents(),
                document.dateTime(),
                document.thumbnail(),
                document.title(),
                document.url()
            );
        }
    }

    public record Meta(Boolean isEnd,
                       int pageableCount,
                       int totalCount) {

        public static Meta from(final BlogApiMetadata metadata) {
            return new Meta(
                metadata.isEnd(),
                metadata.pageableCount(),
                metadata.totalCount()
            );
        }
    }
}
