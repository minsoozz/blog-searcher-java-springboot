package com.github.minsoozz.search.persistence.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import com.github.minsoozz.search.config.TestJpaConfiguration;
import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Import({TestJpaConfiguration.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class PopularSearchCommandTest {

    @Autowired
    private PopularSearchRepository popularSearchRepository;

    @Test
    void 인기검색어를_저장한다() {
        // given
        String query = "minsoo";
        PopularSearchJpaEntity popularSearchEntity = PopularSearchJpaEntity.of(query);

        // when
        PopularSearchJpaEntity savedPopularSearchEntity = popularSearchRepository.save(popularSearchEntity);

        // then
        assertEquals(popularSearchEntity.getCount(), savedPopularSearchEntity.getCount());
        assertEquals(query, savedPopularSearchEntity.getKeyword());
    }
}