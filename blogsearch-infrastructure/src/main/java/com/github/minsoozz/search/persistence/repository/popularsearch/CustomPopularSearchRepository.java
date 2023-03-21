package com.github.minsoozz.search.persistence.repository.popularsearch;

import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import java.util.List;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface CustomPopularSearchRepository {

    List<PopularSearchDto> findTopKeywords(final int limit);
}
