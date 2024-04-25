package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDayChartEntity is a Querydsl query type for DayChartEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayChartEntity extends EntityPathBase<DayChartEntity> {

    private static final long serialVersionUID = 2142631367L;

    public static final QDayChartEntity dayChartEntity = new QDayChartEntity("dayChartEntity");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> productPurchase = createNumber("productPurchase", Integer.class);

    public final NumberPath<Integer> productSales = createNumber("productSales", Integer.class);

    public final NumberPath<Integer> salaryExpense = createNumber("salaryExpense", Integer.class);

    public final NumberPath<Integer> serviceIncome = createNumber("serviceIncome", Integer.class);

    public final NumberPath<Integer> TotalExpense = createNumber("TotalExpense", Integer.class);

    public final NumberPath<Integer> TotalIncome = createNumber("TotalIncome", Integer.class);

    public QDayChartEntity(String variable) {
        super(DayChartEntity.class, forVariable(variable));
    }

    public QDayChartEntity(Path<? extends DayChartEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayChartEntity(PathMetadata metadata) {
        super(DayChartEntity.class, metadata);
    }

}

