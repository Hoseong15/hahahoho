package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSalesEntity is a Querydsl query type for SalesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesEntity extends EntityPathBase<SalesEntity> {

    private static final long serialVersionUID = -1799671379L;

    public static final QSalesEntity salesEntity = new QSalesEntity("salesEntity");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final StringPath cut = createString("cut");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath type = createString("type");

    public QSalesEntity(String variable) {
        super(SalesEntity.class, forVariable(variable));
    }

    public QSalesEntity(Path<? extends SalesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesEntity(PathMetadata metadata) {
        super(SalesEntity.class, metadata);
    }

}

