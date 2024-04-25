package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDesignerCalendarEntity is a Querydsl query type for DesignerCalendarEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDesignerCalendarEntity extends EntityPathBase<DesignerCalendarEntity> {

    private static final long serialVersionUID = 1140355118L;

    public static final QDesignerCalendarEntity designerCalendarEntity = new QDesignerCalendarEntity("designerCalendarEntity");

    public final BooleanPath allDay = createBoolean("allDay");

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final StringPath eventType = createString("eventType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final StringPath whoAmI = createString("whoAmI");

    public QDesignerCalendarEntity(String variable) {
        super(DesignerCalendarEntity.class, forVariable(variable));
    }

    public QDesignerCalendarEntity(Path<? extends DesignerCalendarEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignerCalendarEntity(PathMetadata metadata) {
        super(DesignerCalendarEntity.class, metadata);
    }

}

