package com.github.minsoozz.search.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPopularSearchJpaEntity is a Querydsl query type for PopularSearchJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPopularSearchJpaEntity extends EntityPathBase<PopularSearchJpaEntity> {

    private static final long serialVersionUID = 1336352578L;

    public static final QPopularSearchJpaEntity popularSearchJpaEntity = new QPopularSearchJpaEntity("popularSearchJpaEntity");

    public final com.github.minsoozz.search.config.QBaseEntity _super = new com.github.minsoozz.search.config.QBaseEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keyword = createString("keyword");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> recentSearchedTime = _super.recentSearchedTime;

    public QPopularSearchJpaEntity(String variable) {
        super(PopularSearchJpaEntity.class, forVariable(variable));
    }

    public QPopularSearchJpaEntity(Path<? extends PopularSearchJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPopularSearchJpaEntity(PathMetadata metadata) {
        super(PopularSearchJpaEntity.class, metadata);
    }

}

