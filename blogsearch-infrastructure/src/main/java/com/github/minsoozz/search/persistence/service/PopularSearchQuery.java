package com.github.minsoozz.search.persistence.service;

import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
@Transactional(readOnly = true)
public class PopularSearchQuery {

    private final PopularSearchRepository popularSearchRepository;

    public PopularSearchQuery(final PopularSearchRepository popularSearchRepository) {
        this.popularSearchRepository = popularSearchRepository;
    }

    public Optional<PopularSearchJpaEntity> findByKeyword(final String query) {
        return popularSearchRepository.findByKeyword(query);
    }

    public List<PopularSearchDto> findTopKeywords(final int limit) {
        return popularSearchRepository.findTopKeywords(limit);
    }
}
