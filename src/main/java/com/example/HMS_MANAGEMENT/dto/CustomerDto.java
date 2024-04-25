package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class CustomerDto {


    private Long id;

    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name; // 이름

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstVisit; // 첫 방문일

    @NotBlank(message = "전화번호는 필수 입력입니다.")
    private String tel; // 전화번호

    private String record; // 이용기록

    @NotBlank(message = "디자이너는 필수 선택입니다.")
    private String frequentDesigner; // 자주찾는 디자이너

    private Integer cusCost;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesId")
    private SalesEntity sales;


}