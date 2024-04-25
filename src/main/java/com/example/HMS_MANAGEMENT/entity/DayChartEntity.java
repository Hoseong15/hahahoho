package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class DayChartEntity {

    @Id
    @Column(name = "dayChartId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;  // 해당 기록날짜
    @Column
    private int serviceIncome; // 시술 수입

    @Column
    private int productSales; // 상품 판매 수입

    @Column
    private int TotalIncome; // 전체 수입합계

    @Column
    private int salaryExpense; // 월급 지출

    @Column
    private int productPurchase; // 상품 구입 지출

    @Column
    private int TotalExpense; // 전체 지출합계

}