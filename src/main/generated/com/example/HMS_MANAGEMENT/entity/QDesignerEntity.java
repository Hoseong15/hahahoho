package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDesignerEntity is a Querydsl query type for DesignerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDesignerEntity extends EntityPathBase<DesignerEntity> {

    private static final long serialVersionUID = 142552144L;

    public static final QDesignerEntity designerEntity = new QDesignerEntity("designerEntity");

    public final TimePath<java.time.LocalTime> afterTime = createTime("afterTime", java.time.LocalTime.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final ListPath<DayOffEntity, QDayOffEntity> dayOffs = this.<DayOffEntity, QDayOffEntity>createList("dayOffs", DayOffEntity.class, QDayOffEntity.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> morningTime = createTime("morningTime", java.time.LocalTime.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sal = createNumber("sal", Integer.class);

    public final DatePath<java.time.LocalDate> salDate = createDate("salDate", java.time.LocalDate.class);

    public final StringPath tel = createString("tel");

    public QDesignerEntity(String variable) {
        super(DesignerEntity.class, forVariable(variable));
    }

    public QDesignerEntity(Path<? extends DesignerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignerEntity(PathMetadata metadata) {
        super(DesignerEntity.class, metadata);
    }

}

