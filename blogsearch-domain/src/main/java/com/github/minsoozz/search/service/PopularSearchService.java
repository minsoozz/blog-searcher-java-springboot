package com.github.minsoozz.search.service;

import com.github.minsoozz.search.converter.PopularSearchConverter;
import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.exception.DistributedLockAcquisitionFailedException;
import com.github.minsoozz.search.exception.DistributedLockingException;
import com.github.minsoozz.search.exception.FailedToAcquireLockException;
import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.facade.PopularSearchFacade;
import com.github.minsoozz.search.persistence.service.PopularSearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class PopularSearchService {

    private final PopularSearchQuery popularSearchQuery;
    private final PopularSearchFacade popularSearchFacade;

    public PopularSearchService(
            final PopularSearchQuery popularSearchQuery,
            final PopularSearchFacade popularSearchFacade) {
        this.popularSearchQuery = popularSearchQuery;
        this.popularSearchFacade = popularSearchFacade;
    }

    public PopularSearch savePopularSearch(final String query) {
        try {
            PopularSearchDto popularSearchDto = popularSearchFacade.savePopularSearch(query);
            return PopularSearchConverter.toPopularSearch(popularSearchDto);
        } catch (DistributedLockingException | DistributedLockAcquisitionFailedException e) {
            throw new FailedToAcquireLockException(e);
        }
    }

    public List<PopularSearch> searchPopularKeywords() {
        return popularSearchQuery.findTop10Keywords()
                .stream()
                .map(PopularSearch::of)
                .toList();
    }
}
