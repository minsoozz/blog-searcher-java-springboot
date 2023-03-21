package com.github.minsoozz.search.exception;

public class PopularSearchEntityNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "인기검색어를 검색하는 도중 오류가 발생했습니다";

    public PopularSearchEntityNotFoundException() {
        super(MESSAGE_FORMAT);
    }
}
