package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginEntity is a Querydsl query type for LoginEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoginEntity extends EntityPathBase<LoginEntity> {

    private static final long serialVersionUID = -1876042166L;

    public static final QLoginEntity loginEntity = new QLoginEntity("loginEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath role = createString("role");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public QLoginEntity(String variable) {
        super(LoginEntity.class, forVariable(variable));
    }

    public QLoginEntity(Path<? extends LoginEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginEntity(PathMetadata metadata) {
        super(LoginEntity.class, metadata);
    }

}

