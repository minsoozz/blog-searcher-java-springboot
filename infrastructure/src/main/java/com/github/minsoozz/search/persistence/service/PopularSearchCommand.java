package com.github.minsoozz.search.persistence.service;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import org.springframework.stereotype.Service;

/**
 * @author minsoozz
 * @date 2023.07.01
 */
@Service
public class PopularSearchCommand {

    private final PopularSearchRepository popularSearchRepository;

    public PopularSearchCommand(PopularSearchRepository popularSearchRepository) {
        this.popularSearchRepository = popularSearchRepository;
    }

    public PopularSearchJpaEntity save(PopularSearchJpaEntity popularSearchJpaEntity) {
        return popularSearchRepository.save(popularSearchJpaEntity);
    }
}
