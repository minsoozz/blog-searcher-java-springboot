package com.github.minsoozz.search.spec;

import com.github.minsoozz.search.service.PopularSearchDto;

import java.util.List;

public interface PopularSpec {

    PopularSearchDto savePopularSearch(String query);

    List<PopularSearchDto> searchPopularKeywords();
}
