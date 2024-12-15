package com.github.minsoozz.search.blogsearch.api;

import com.github.minsoozz.search.blogsearch.dto.BlogSearchRequestDto;
import com.github.minsoozz.search.common.dto.BaseResponseDto;
import com.github.minsoozz.search.domain.blog.BlogSearch;
import com.github.minsoozz.search.dto.BlogSearchRequest;
import com.github.minsoozz.search.facade.BlogSearchPopularFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@RestController
@RequestMapping("/api/v1/blog")
public class BlogSearchRestController {

    private final BlogSearchPopularFacade blogSearchPopularFacade;

    public BlogSearchRestController(BlogSearchPopularFacade blogSearchPopularFacade) {
        this.blogSearchPopularFacade = blogSearchPopularFacade;
    }

    /**
     * 블로그 API 조회를 조회한다
     */
    @GetMapping("/search")
    public BaseResponseDto<BlogSearch> getBlogSearchResults(BlogSearchRequestDto requestDto) {
        return BaseResponseDto.ok(blogSearchPopularFacade.searchBlog(
                new BlogSearchRequest(requestDto.query(), requestDto.page(), requestDto.sort())));
    }
}
