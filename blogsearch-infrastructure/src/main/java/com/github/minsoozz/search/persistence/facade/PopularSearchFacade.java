package com.github.minsoozz.search.persistence.facade;

import com.github.minsoozz.search.lock.DistributedLock;
import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.service.PopularSearchCommand;
import com.github.minsoozz.search.persistence.service.PopularSearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
@Transactional
public class PopularSearchFacade {

    private static final String LOCK_NAME = "popular-lock";

    private final PopularSearchCommand popularSearchCommand;
    private final PopularSearchQuery popularSearchQuery;
    private final DistributedLock distributedLock;

    public PopularSearchFacade(final PopularSearchCommand popularSearchCommand,
        final PopularSearchQuery popularSearchQuery, final DistributedLock distributedLock) {
        this.popularSearchCommand = popularSearchCommand;
        this.popularSearchQuery = popularSearchQuery;
        this.distributedLock = distributedLock;
    }

    public PopularSearchDto saveSearchHistory(final String query) {
        return distributedLock.acquire(LOCK_NAME, () -> {
            boolean exists = popularSearchQuery.existsByKeyword(query);
            if (exists) {
                PopularSearchJpaEntity popularSearchJpaEntity = popularSearchQuery.findByKeyword(query).orElseThrow();
                popularSearchJpaEntity.increaseCount();
                popularSearchCommand.save(popularSearchJpaEntity);
                return new PopularSearchDto(popularSearchJpaEntity.getKeyword(), popularSearchJpaEntity.getCount());
            }
            PopularSearchJpaEntity savedEntity = popularSearchCommand.save(PopularSearchJpaEntity.of(query));
            return new PopularSearchDto(savedEntity.getKeyword(), savedEntity.getCount());
        });
    }
}
