package com.github.minsoozz.search.persistence.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import com.github.minsoozz.search.config.TestJpaConfiguration;
import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import com.github.minsoozz.search.persistence.repository.popularsearch.PopularSearchRepository;
import java.util.List;
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
class PopularSearchQueryTest {

    @Autowired
    PopularSearchRepository popularSearchRepository;

    @Test
    void 키워드가_존재하는지_조회한다() {
        // given
        final String query = "민수";

        // when
        boolean exists = popularSearchRepository.existsByKeyword(query);

        // then
        assertTrue(exists);
    }

    @Test
    void 키워드로_엔티티를_조회한다() {
        // given
        final String query = "은행";

        // when
        PopularSearchJpaEntity popularSearchJpaEntity = popularSearchRepository.findByKeyword(query).orElseThrow();

        // then
        assertEquals(query, popularSearchJpaEntity.getKeyword());
    }

    @Test
    void 인기검색어_최대_10개를_조회한다() {
        // given
        final long count = 10;

        // when
        List<PopularSearchDto> keywords = popularSearchRepository.findTop10Keywords();

        // then
        assertEquals(count, keywords.size());
    }

    @Test
    void 인기검색어_순위가_정확한지_검증한다() {
        // when
        List<PopularSearchDto> keywords = popularSearchRepository.findTop10Keywords();

        assertEquals(10, keywords.size());
        assertEquals("투자상품", keywords.get(0).keyword());
        assertEquals("은행", keywords.get(1).keyword());
        assertEquals("외환", keywords.get(2).keyword());
        assertEquals("보험상품", keywords.get(3).keyword());
        assertEquals("민수", keywords.get(4).keyword());
        assertEquals("신용카드", keywords.get(5).keyword());
        assertEquals("주식", keywords.get(6).keyword());
        assertEquals("부동산대출", keywords.get(7).keyword());
        assertEquals("카드론", keywords.get(8).keyword());
        assertEquals("대출", keywords.get(9).keyword());
    }
}