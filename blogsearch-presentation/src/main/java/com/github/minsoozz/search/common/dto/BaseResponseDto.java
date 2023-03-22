package com.github.minsoozz.search.common.dto;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public record BaseResponseDto<T>(String message, T data) {

    public static <T> BaseResponseDto<T> ok(T data) {
        return new BaseResponseDto<>("success", data);
    }

}
