package com.example.HMS_MANAGEMENT.entity;

import com.example.HMS_MANAGEMENT.dto.SalesDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class CustomerEntity {

    @Id
    @Column(name = "customerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name; // 이름

    @Column
    private LocalDate firstVisit; // 첫 방문일

    @Column
    private String tel; // 전화번호

    @Column
    private String record; // 이용기록

    @Column
    private String frequentDesigner; // 자주찾는 디자이너

    @Column
    private int cusCost;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesId")
    private SalesEntity sales;

}