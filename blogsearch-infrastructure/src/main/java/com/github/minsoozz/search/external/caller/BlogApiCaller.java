package com.github.minsoozz.search.external.caller;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface BlogApiCaller {

    ApiResponseDto searchBlogs(final String query, final Integer page, final String sort);
}
