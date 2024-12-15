package com.github.minsoozz.search.domain.popularsearch;


import com.github.minsoozz.search.service.PopularSearchDto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record PopularSearch(String keyword,
                            int count
) {

    public static PopularSearch of(PopularSearchDto popularSearchDto) {
        return new PopularSearch(popularSearchDto.keyword(), popularSearchDto.count());
    }
}
