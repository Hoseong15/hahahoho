package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerDetailDto {

    private Long id;
    private Long customerId;
    private String name; // 이름

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visit; // 첫 방문일

    private String cutType; // 이용기록

    @NotBlank(message = "디자이너는 필수 선택입니다.")
    private String designer; // 자주찾는 디자이너

    private Integer cost;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesId")
    private SalesEntity sales;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;
}