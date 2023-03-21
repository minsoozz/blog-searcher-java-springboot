package com.github.minsoozz.search.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record PopularSearchDto(String keyword, int count) {

    @QueryProjection
    public PopularSearchDto {
    }
}
