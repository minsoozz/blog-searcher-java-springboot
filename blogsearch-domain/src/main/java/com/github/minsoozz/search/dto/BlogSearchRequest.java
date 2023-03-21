package com.github.minsoozz.search.dto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BlogSearchRequest(
    String query,
    Integer page,
    String sort
) {

}
