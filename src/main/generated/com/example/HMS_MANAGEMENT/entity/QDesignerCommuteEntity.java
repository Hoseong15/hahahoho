package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDesignerCommuteEntity is a Querydsl query type for DesignerCommuteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDesignerCommuteEntity extends EntityPathBase<DesignerCommuteEntity> {

    private static final long serialVersionUID = 905745040L;

    public static final QDesignerCommuteEntity designerCommuteEntity = new QDesignerCommuteEntity("designerCommuteEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDesignerCommuteEntity(String variable) {
        super(DesignerCommuteEntity.class, forVariable(variable));
    }

    public QDesignerCommuteEntity(Path<? extends DesignerCommuteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignerCommuteEntity(PathMetadata metadata) {
        super(DesignerCommuteEntity.class, metadata);
    }

}

