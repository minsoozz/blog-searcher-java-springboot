package com.github.minsoozz.search.service;

import com.github.minsoozz.search.converter.PopularSearchConverter;
import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.exception.FailedToAcquireLockException;
import com.github.minsoozz.search.spec.PopularSpec;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class PopularSearchService {

    private final PopularSpec popularSpec;

    public PopularSearchService(PopularSpec popularSpec) {
        this.popularSpec = popularSpec;
    }

    public PopularSearch savePopularSearch(String query) {
        try {
            PopularSearchDto popularSearchDto = popularSpec.savePopularSearch(query);
            return PopularSearchConverter.toPopularSearch(popularSearchDto);
        } catch (Exception e) {
            throw new FailedToAcquireLockException(e);
        }
    }

    public List<PopularSearch> searchPopularKeywords() {
        return popularSpec.searchPopularKeywords()
                .stream()
                .map(PopularSearch::of)
                .toList();
    }
}
