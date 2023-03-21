package com.github.minsoozz.search.external.enums;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public enum ApiQueryParameter {
    KAKAO("query", "recency", "page", "size"),
    NAVER("query", "date", "start", "display");

    private final String query;
    private final String sort;
    private final String page;
    private final String size;

    ApiQueryParameter(String query, String sort, String page, String size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    public String getQuery() {
        return query;
    }

    public String getSort() {
        return sort;
    }

    public String getPage() {
        return page;
    }

    public String getSize() {
        return size;
    }
}
