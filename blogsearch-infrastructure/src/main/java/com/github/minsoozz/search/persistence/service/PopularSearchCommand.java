package com.github.minsoozz.search.persistence.service;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import org.springframework.stereotype.Service;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class PopularSearchCommand {

    private final PopularSearchRepository popularSearchRepository;

    public PopularSearchCommand(final PopularSearchRepository popularSearchRepository) {
        this.popularSearchRepository = popularSearchRepository;
    }

    public PopularSearchJpaEntity save(final PopularSearchJpaEntity popularSearchJpaEntity) {
        return popularSearchRepository.save(popularSearchJpaEntity);
    }
}
