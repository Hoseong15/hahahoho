package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerDetailEntity {

    @Id
    @Column(name = "customerDetailId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; // 이름

    @Column
    private LocalDate visit; // 첫 방문일

    @Column
    private String cutType; // 이용기록

    @Column
    private String designer; // 자주찾는 디자이너

    @Column
    private int cost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesId")
    private SalesEntity sales;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;
}