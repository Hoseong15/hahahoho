package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthChartDto {

    private int month; // 월
    private int totalIncome; // 월간 수입총합
    private int totalExpense; // 주간 지출총합
}
