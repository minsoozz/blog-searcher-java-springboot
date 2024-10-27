package com.github.minsoozz.search.blogsearch.dto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogSearchRequestDto(
    String query,
    Integer page,
    String sort
) {

    public BlogSearchRequestDto {
        if (sort == null) {
            sort = "accuracy";
        }
        if (page == null) {
            page = 1;
        }
    }
}
