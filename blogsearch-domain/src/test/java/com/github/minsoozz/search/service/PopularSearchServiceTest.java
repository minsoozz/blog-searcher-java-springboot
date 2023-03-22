package com.github.minsoozz.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.facade.PopularSearchFacade;
import com.github.minsoozz.search.persistence.service.PopularSearchQuery;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PopularSearchServiceTest {

    @InjectMocks
    PopularSearchService popularSearchService;

    @Mock
    PopularSearchQuery popularSearchQuery;

    @Mock
    PopularSearchFacade popularSearchFacade;

    @Test
    void 인기검색어_도메인을_반환한다() {
        // given
        final String query = "민수";
        final int count = 2;
        PopularSearchDto popularSearchDto = new PopularSearchDto(query, count);
        when(popularSearchFacade.savePopularSearch(query)).thenReturn(popularSearchDto);

        // when
        PopularSearch popularSearch = popularSearchService.savePopularSearch(query);

        // then
        assertNotEquals(100, popularSearch.count());
        assertEquals(query, popularSearch.keyword());
        assertEquals(count, popularSearch.count());
    }

    @Test
    void 인기검색어_목록을_반환한다() {
        // given
        List<PopularSearchDto> fixture = fixture();

        // when
        when(popularSearchQuery.findTop10Keywords()).thenReturn(fixture);

        // then
        List<PopularSearch> popularSearches = popularSearchService.searchPopularKeywords();
        assertNotEquals(100, popularSearches.size());
        assertEquals(fixture.size(), popularSearches.size());
    }

    private List<PopularSearchDto> fixture() {
        return List.of(
            new PopularSearchDto("은행", 1),
            new PopularSearchDto("투자", 2),
            new PopularSearchDto("주식", 3),
            new PopularSearchDto("적금", 4),
            new PopularSearchDto("예금", 5)
        );
    }
}