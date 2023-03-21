package com.github.minsoozz.search.facade;

import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.service.BlogSearchService;
import com.github.minsoozz.search.service.PopularSearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
@Transactional
public class BlogSearchPopularFacade {

    private final BlogSearchService blogSearchService;
    private final PopularSearchService popularSearchService;

    public BlogSearchPopularFacade(final BlogSearchService blogSearchService,
        final PopularSearchService popularSearchService) {
        this.blogSearchService = blogSearchService;
        this.popularSearchService = popularSearchService;
    }

    public BlogSearch searchBlog(final BlogSearchRequest blogSearchRequest) {
        BlogSearch blogSearch = blogSearchService.getBlogSearchResults(blogSearchRequest);
        popularSearchService.saveSearchHistory(blogSearchRequest.query());
        return blogSearch;
    }
}