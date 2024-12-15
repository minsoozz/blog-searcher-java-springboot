package com.github.minsoozz.search.popularsearch.api;

import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.service.PopularSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PopularSearchRestController.class)
class PopularSearchRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PopularSearchService popularSearchService;

    @Test
    void 인기_검색어를_조회한다() throws Exception {
        final String uri = "/api/v1/popular/search";
        given(popularSearchService.searchPopularKeywords()).willReturn(getFixture());

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data[4].keyword").value("예금"))
                .andExpect(jsonPath("$.data[4].count").value(5))
                .andExpect(jsonPath("$.data[3].keyword").value("적금"))
                .andExpect(jsonPath("$.data[3].count").value(4))
                .andExpect(jsonPath("$.data[2].keyword").value("주식"))
                .andExpect(jsonPath("$.data[2].count").value(3))
                .andExpect(jsonPath("$.data[1].keyword").value("투자"))
                .andExpect(jsonPath("$.data[1].count").value(2))
                .andExpect(jsonPath("$.data[0].keyword").value("은행"))
                .andExpect(jsonPath("$.data[0].count").value(1))
                .andDo(print());
    }

    private List<PopularSearch> getFixture() {
        return List.of(
                new PopularSearch("은행", 1),
                new PopularSearch("투자", 2),
                new PopularSearch("주식", 3),
                new PopularSearch("적금", 4),
                new PopularSearch("예금", 5)
        );
    }
}