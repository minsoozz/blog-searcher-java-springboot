package com.github.minsoozz.search.spec;

public interface ApiSpec {

    ApiResponseDto searchBlogs(String query, final Integer page, final String sort);
}
