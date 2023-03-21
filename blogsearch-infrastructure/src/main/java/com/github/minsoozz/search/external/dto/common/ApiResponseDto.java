package com.github.minsoozz.search.external.dto.common;

import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface ApiResponseDto {

    List<BlogApiPost> getContents();

    BlogApiMetadata getMeta();
}
