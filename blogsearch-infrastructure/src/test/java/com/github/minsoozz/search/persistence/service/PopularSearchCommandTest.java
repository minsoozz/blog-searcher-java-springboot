package com.github.minsoozz.search.persistence.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PopularSearchCommandTest {

    @InjectMocks
    private PopularSearchCommand popularSearchCommand;

    @Mock
    private PopularSearchRepository popularSearchRepository;

    @Test
    @Order(1)
    void 새로운_인기검색어를_저장한다() {
        // given
        String query = "minsoo";
        PopularSearchJpaEntity popularSearchEntity = PopularSearchJpaEntity.of(query);

        // when
        when(popularSearchRepository.save(any())).thenReturn(popularSearchEntity);
        PopularSearchJpaEntity savedPopularSearchEntity = popularSearchCommand.save(popularSearchEntity);

        // then
        assertEquals(popularSearchEntity.getCount(), savedPopularSearchEntity.getCount());
        assertEquals(query, popularSearchEntity.getKeyword());
    }
}