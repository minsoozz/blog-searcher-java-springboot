package com.github.minsoozz.search.converter;

import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.persistence.dto.PopularSearchDto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public class PopularSearchConverter {

    private PopularSearchConverter() {

    }

    public static PopularSearch toPopularSearch(final PopularSearchDto popularSearchDto) {
        return new PopularSearch(popularSearchDto.keyword(), popularSearchDto.count());
    }
}
