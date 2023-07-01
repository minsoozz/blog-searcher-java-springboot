package com.github.minsoozz.search.persistence.entity;

import com.github.minsoozz.search.config.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author minsoozz
 * @date 2023.07.01
 */
@Entity
@Table(name = "popular_search",
    indexes = {@Index(name = "idx_popular_search_keyword", columnList = "keyword")})
public class PopularSearchJpaEntity extends BaseEntity {

    protected PopularSearchJpaEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String keyword;

    private int count;

    public PopularSearchJpaEntity(String keyword) {
        this.keyword = keyword;
    }

    public static PopularSearchJpaEntity of(String query) {
        return new PopularSearchJpaEntity(query);
    }

    public String getKeyword() {
        return keyword;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
    }

    @PrePersist
    public void prePersist() {
        this.count = 1;
    }
}
