package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerEntity is a Querydsl query type for CustomerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerEntity extends EntityPathBase<CustomerEntity> {

    private static final long serialVersionUID = -2102619389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerEntity customerEntity = new QCustomerEntity("customerEntity");

    public final NumberPath<Integer> cusCost = createNumber("cusCost", Integer.class);

    public final DatePath<java.time.LocalDate> firstVisit = createDate("firstVisit", java.time.LocalDate.class);

    public final StringPath frequentDesigner = createString("frequentDesigner");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath record = createString("record");

    public final QSalesEntity sales;

    public final StringPath tel = createString("tel");

    public QCustomerEntity(String variable) {
        this(CustomerEntity.class, forVariable(variable), INITS);
    }

    public QCustomerEntity(Path<? extends CustomerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerEntity(PathMetadata metadata, PathInits inits) {
        this(CustomerEntity.class, metadata, inits);
    }

    public QCustomerEntity(Class<? extends CustomerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sales = inits.isInitialized("sales") ? new QSalesEntity(forProperty("sales")) : null;
    }

}

