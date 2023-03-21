package com.github.minsoozz.search.external.dto.common;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogApiMetadata(Boolean isEnd, int pageableCount, int totalCount) {

    public static BlogApiMetadata of(Boolean isEnd, int pageableCount, int totalCount) {
        return new BlogApiMetadata(isEnd, pageableCount, totalCount);
    }
}
