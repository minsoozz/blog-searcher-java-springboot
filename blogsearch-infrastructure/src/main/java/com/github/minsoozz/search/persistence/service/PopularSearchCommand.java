package com.github.minsoozz.search.persistence.service;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
@Transactional
public class PopularSearchCommand {

    private final PopularSearchRepository popularSearchRepository;

    public PopularSearchCommand(final PopularSearchRepository popularSearchRepository) {
        this.popularSearchRepository = popularSearchRepository;
    }

    public PopularSearchJpaEntity save(final PopularSearchJpaEntity popularSearchJpaEntity) {
        popularSearchJpaEntity.increaseCount();
        return popularSearchRepository.save(popularSearchJpaEntity);
    }

    public PopularSearchJpaEntity update(final PopularSearchJpaEntity popularSearchJpaEntity) {
        popularSearchJpaEntity.increaseCount();
        return popularSearchJpaEntity;
    }
}
