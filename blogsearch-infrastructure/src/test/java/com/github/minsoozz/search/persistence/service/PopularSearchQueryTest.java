package com.github.minsoozz.search.persistence.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.github.minsoozz.search.exception.PopularSearchEntityNotFoundException;
import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ObjectUtils;

@ExtendWith(MockitoExtension.class)
class PopularSearchQueryTest {

    @InjectMocks
    private PopularSearchQuery popularSearchQuery;

    @Mock
    private PopularSearchRepository popularSearchRepository;

    private final String query = "minsoo";

    @Test
    void 이미_존재하는_엔티티를_찾는다() {
        // given
        PopularSearchJpaEntity savedPopularSearchJpa = PopularSearchJpaEntity.of(query);

        // when
        when(popularSearchRepository.findByKeyword(query)).thenReturn(Optional.of(savedPopularSearchJpa));
        PopularSearchJpaEntity popularSearchJpa = popularSearchQuery.findByKeyword(query)
            .orElseThrow(PopularSearchEntityNotFoundException::new);

        // then
        assertEquals(query, popularSearchJpa.getKeyword());
    }

    @Test
    void 존재하지_않는_엔티티를_찾는다() {

        // when
        when(popularSearchRepository.findByKeyword(query)).thenReturn(Optional.empty());
        Optional<PopularSearchJpaEntity> popularSearchJpa = popularSearchQuery.findByKeyword(query);

        // then
        assertTrue(ObjectUtils.isEmpty(popularSearchJpa));
    }
}