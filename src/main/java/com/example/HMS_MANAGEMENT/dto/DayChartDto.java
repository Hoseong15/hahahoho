package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DayChartDto {


    private Long id;
    private LocalDate date;  // 해당 기록날짜

    private int serviceIncome; // 시술 수입
    private int productSales; // 상품 판매 수입
    private int TotalIncome; // 전체 수입합계

    private int salaryExpense; // 월급 지출
    private int productPurchase; // 상품 구입 지출
    private int TotalExpense; // 전체 지출합계




}