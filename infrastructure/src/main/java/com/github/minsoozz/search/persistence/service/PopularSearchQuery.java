package com.github.minsoozz.search.persistence.service;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import com.github.minsoozz.search.service.PopularSearchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
@Transactional(readOnly = true)
public class PopularSearchQuery {

    private final PopularSearchRepository popularSearchRepository;

    public PopularSearchQuery(PopularSearchRepository popularSearchRepository) {
        this.popularSearchRepository = popularSearchRepository;
    }

    public Optional<PopularSearchJpaEntity> findByKeyword(String query) {
        return popularSearchRepository.findByKeyword(query);
    }

    public List<PopularSearchDto> findTop10Keywords() {
        return popularSearchRepository.findTop10Keywords();
    }

    public boolean existsByKeyword(String query) {
        return popularSearchRepository.existsByKeyword(query);
    }
}
