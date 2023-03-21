package com.github.minsoozz.search.persistence.repository.popularsearch.impl;

import static com.github.minsoozz.search.persistence.entity.QPopularSearchJpaEntity.popularSearchJpaEntity;

import com.github.minsoozz.search.persistence.dto.PopularSearchDto;
import com.github.minsoozz.search.persistence.dto.QPopularSearchDto;
import com.github.minsoozz.search.persistence.repository.popularsearch.CustomPopularSearchRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
@Repository
public class CustomPopularSearchRepositoryImpl implements CustomPopularSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CustomPopularSearchRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<PopularSearchDto> findTopKeywords(final int limit) {
        return jpaQueryFactory.select(
                new QPopularSearchDto(
                    popularSearchJpaEntity.keyword,
                    popularSearchJpaEntity.count))
            .from(popularSearchJpaEntity)
            .limit(limit)
            .orderBy(popularSearchJpaEntity.count.desc())
            .fetch();
    }
}
