package com.github.minsoozz.search.popularsearch.api;

import com.github.minsoozz.search.common.dto.BaseResponseDto;
import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.service.PopularSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@RestController
@RequestMapping("/api/v1/popular")
public class PopularSearchRestController {

    private final PopularSearchService popularSearchService;

    public PopularSearchRestController(PopularSearchService popularSearchService) {
        this.popularSearchService = popularSearchService;
    }

    /**
     * 인기 검색어 조회를 조회한다
     */
    @GetMapping("/search")
    public BaseResponseDto<List<PopularSearch>> getPopularSearchResults() {
        return BaseResponseDto.ok(popularSearchService.searchPopularKeywords());
    }
}
