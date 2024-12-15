package com.github.minsoozz.search.persistence.repository.popularsearch;

import com.github.minsoozz.search.persistence.entity.PopularSearchJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author minsoozz
 * @date 2023.03.22
 */
public interface PopularSearchRepository extends JpaRepository<PopularSearchJpaEntity, Long>,
        CustomPopularSearchRepository {

    Optional<PopularSearchJpaEntity> findByKeyword(String query);

    boolean existsByKeyword(String query);
}
