package com.github.minsoozz.search.facade;

import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.service.BlogSearchService;
import com.github.minsoozz.search.service.PopularSearchService;
import org.springframework.stereotype.Service;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
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
        popularSearchService.savePopularSearch(blogSearchRequest.query());
        return blogSearch;
    }
}
