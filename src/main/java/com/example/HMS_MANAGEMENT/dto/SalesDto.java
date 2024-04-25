package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class SalesDto {

    private Long id;

    @NotBlank(message = "시술종류는 필수 입력 값입니다.")
    private String cut;

    @NotBlank(message = "커트종류는 필수 입력 값입니다.")
    private String type;

    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private Integer cost;
}