package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.entity.DayOffEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class DesignerDto {


    private Long id;
    @NotNull(message = "이름을 입력 해주세요.")
    @NotEmpty(message = "이름을 입력 해주세요.")
    private String name;
    @NotNull(message = "전화번호를 입력 해주세요.")
    @NotEmpty(message = "전화번호를 입력 해주세요.")
    private String tel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "등록일을 선택 해주세요.")
    private LocalDate date;
    @NumberFormat(pattern = "#,###,###")
    @NotNull(message = "월급을 입력 해주세요.")
    private Integer sal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "월급 지급일을 선택 해주세요.")
    private LocalDate salDate;
    @NotNull(message = "시간과 분을 입력 해주세요.")
    private String morningTime;
    private String afterTime;
    @Email(message = "올바른 이메일 형식을 입력 해주세요.")
    @NotEmpty(message = "이메일을 입력 해주세요.")
    private String email;
    private List<String> dayOffList;

}
