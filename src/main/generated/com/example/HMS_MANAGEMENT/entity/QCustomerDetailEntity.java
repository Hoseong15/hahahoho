package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerDetailEntity is a Querydsl query type for CustomerDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerDetailEntity extends EntityPathBase<CustomerDetailEntity> {

    private static final long serialVersionUID = 338329268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerDetailEntity customerDetailEntity = new QCustomerDetailEntity("customerDetailEntity");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final QCustomerEntity customer;

    public final StringPath cutType = createString("cutType");

    public final StringPath designer = createString("designer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QSalesEntity sales;

    public final DatePath<java.time.LocalDate> visit = createDate("visit", java.time.LocalDate.class);

    public QCustomerDetailEntity(String variable) {
        this(CustomerDetailEntity.class, forVariable(variable), INITS);
    }

    public QCustomerDetailEntity(Path<? extends CustomerDetailEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerDetailEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerDetailEntity(PathMetadata metadata, PathInits inits) {
        this(CustomerDetailEntity.class, metadata, inits);
    }

    public QCustomerDetailEntity(Class<? extends CustomerDetailEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomerEntity(forProperty("customer"), inits.get("customer")) : null;
        this.sales = inits.isInitialized("sales") ? new QSalesEntity(forProperty("sales")) : null;
    }

}

