package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDayOffEntity is a Querydsl query type for DayOffEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayOffEntity extends EntityPathBase<DayOffEntity> {

    private static final long serialVersionUID = 2115206168L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDayOffEntity dayOffEntity = new QDayOffEntity("dayOffEntity");

    public final StringPath day = createString("day");

    public final QDesignerEntity designer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDayOffEntity(String variable) {
        this(DayOffEntity.class, forVariable(variable), INITS);
    }

    public QDayOffEntity(Path<? extends DayOffEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDayOffEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDayOffEntity(PathMetadata metadata, PathInits inits) {
        this(DayOffEntity.class, metadata, inits);
    }

    public QDayOffEntity(Class<? extends DayOffEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.designer = inits.isInitialized("designer") ? new QDesignerEntity(forProperty("designer")) : null;
    }

}

