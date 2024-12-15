package com.github.minsoozz.search.spec;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogApiMetadata(
        Boolean isEnd,
        int pageableCount,
        int totalCount
) {

}
