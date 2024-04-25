package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {
    private String designerName;
    private String designerEmail;
    private Integer basicSal;
    private Integer overtimeAllowance;
    private Integer mealAllowance;
    private Integer pension;
    private Integer healthInsurance;
    private Integer employmentInsurance;
    private Integer incomeTax;
    private Integer netSalary;
}