package com.github.minsoozz.search.service;

import com.github.minsoozz.search.converter.BlogSearchConverter;
import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.exception.BlogSearchException;
import com.github.minsoozz.search.spec.ApiResponseDto;
import com.github.minsoozz.search.spec.ApiSpec;
import org.springframework.stereotype.Service;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class BlogSearchService {

    private final ApiSpec apiSpec;

    public BlogSearchService(ApiSpec apiSpec) {
        this.apiSpec = apiSpec;
    }

    public BlogSearch getBlogSearchResults(BlogSearchRequest blogSearchRequest) {
        try {
            ApiResponseDto responseDto = apiSpec.searchBlogs(blogSearchRequest.query(), blogSearchRequest.page(), blogSearchRequest.sort());
            return BlogSearchConverter.toBlog(responseDto);
        } catch (Exception e) {
            throw new BlogSearchException(e);
        }
    }
}
