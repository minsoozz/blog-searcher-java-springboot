package com.github.minsoozz.search.persistence.facade;

import com.github.minsoozz.search.annotations.DistributedLock;
import com.github.minsoozz.search.exception.PopularSearchEntityNotFoundException;
import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.service.PopularSearchCommand;
import com.github.minsoozz.search.persistence.service.PopularSearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minsoozz
 * @date 2023.07.01
 */
@Service
@Transactional
public class PopularSearchFacade {

    private final PopularSearchCommand popularSearchCommand;
    private final PopularSearchQuery popularSearchQuery;

    public PopularSearchFacade(
        final PopularSearchCommand popularSearchCommand,
        final PopularSearchQuery popularSearchQuery) {
        this.popularSearchCommand = popularSearchCommand;
        this.popularSearchQuery = popularSearchQuery;
    }

    @DistributedLock(key = "#query")
    public PopularSearchDto savePopularSearch(final String query) {
        boolean exists = popularSearchQuery.existsByKeyword(query);
        if (exists) {
            PopularSearchJpaEntity popularSearchJpaEntity = popularSearchQuery.findByKeyword(query)
                .orElseThrow(PopularSearchEntityNotFoundException::new);
            popularSearchJpaEntity.increaseCount();
            popularSearchCommand.save(popularSearchJpaEntity);
            return new PopularSearchDto(popularSearchJpaEntity.getKeyword(), popularSearchJpaEntity.getCount());
        }
        PopularSearchJpaEntity savedEntity = popularSearchCommand.save(PopularSearchJpaEntity.of(query));
        return new PopularSearchDto(savedEntity.getKeyword(), savedEntity.getCount());
    }
}
