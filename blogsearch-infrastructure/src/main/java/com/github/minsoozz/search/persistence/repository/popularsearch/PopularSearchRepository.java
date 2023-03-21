package com.github.minsoozz.search.persistence.repository.popularsearch;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface PopularSearchRepository extends JpaRepository<PopularSearchJpaEntity, Long>,
    CustomPopularSearchRepository {

    Optional<PopularSearchJpaEntity> findByKeyword(final String query);

    boolean existsByKeyword(final String query);
}
