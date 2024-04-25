package com.example.HMS_MANAGEMENT.entity;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class InvenEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 제품 번호

    @Column
    private String itemNm; // 제품 이름

    @Column
    private Integer idCode; // 제품 코드

    @Column
    private Integer count; // 제품 수량

    @Column
    private String idClass; // 제품 분류

    @Column
    private Integer itemL; // 제품 용량

    @Enumerated(EnumType.STRING)
    private InvenStatus invenStatus; // 분류

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @Column
    private Integer cash; // 개당 가격

    @Column
    private Integer buyCash;

    @Column
    private Integer sellCash;
}