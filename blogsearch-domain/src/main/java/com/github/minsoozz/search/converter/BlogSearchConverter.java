package com.github.minsoozz.search.converter;

import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.domain.blog.BlogSearch.Document;
import com.github.minsoozz.search.domain.blog.BlogSearch.Meta;
import com.github.minsoozz.search.external.dto.common.ApiResponseDto;
import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class BlogSearchConverter {

    private BlogSearchConverter() {
    }

    public static BlogSearch toBlog(final ApiResponseDto apiResponseDto) {
        List<Document> documents = apiResponseDto.getContents()
            .stream()
            .map(Document::from)
            .toList();
        Meta meta = Meta.from(apiResponseDto.getMeta());
        return BlogSearch.of(documents, meta);
    }
}
