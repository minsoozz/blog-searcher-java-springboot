package com.github.minsoozz.search.popularsearch.api;

import com.github.minsoozz.search.domain.popularsearch.PopularSearch;
import com.github.minsoozz.search.popularsearch.dto.BaseResponseDto;
import com.github.minsoozz.search.service.PopularSearchService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@RestController
@RequestMapping("/api/v1/popular")
public class PopularSearchRestController {

    private final PopularSearchService popularSearchService;

    public PopularSearchRestController(final PopularSearchService popularSearchService) {
        this.popularSearchService = popularSearchService;
    }

    @GetMapping("/search")
    public BaseResponseDto<List<PopularSearch>> getPopularSearchResults() {
        return BaseResponseDto.ok(popularSearchService.searchPopularKeywords());
    }
}
