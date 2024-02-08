package com.github.minsoozz.search.service;

import com.github.minsoozz.search.converter.BlogSearchConverter;
import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.exception.ApiBadRequestException;
import com.github.minsoozz.search.exception.BlogSearchException;
import com.github.minsoozz.search.external.caller.BlogApiCaller;
import com.github.minsoozz.search.external.dto.common.ApiResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Service
public class BlogSearchService {

    private static final Logger logger = LoggerFactory.getLogger(BlogSearchService.class);
    private final BlogApiCaller blogApiCaller;

    public BlogSearchService(@Qualifier("kakaoApiCaller") final BlogApiCaller blogApiCaller) {
        this.blogApiCaller = blogApiCaller;
    }

    public BlogSearch getBlogSearchResults(final BlogSearchRequest blogSearchRequest) {
        try {
            ApiResponseDto responseDto = blogApiCaller.searchBlogs(
                    blogSearchRequest.query(),
                    blogSearchRequest.page(),
                    blogSearchRequest.sort());
            return BlogSearchConverter.toBlog(responseDto);
        } catch (ApiBadRequestException e) {
            throw new BlogSearchException(e);
        }
    }
}
