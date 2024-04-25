package com.example.HMS_MANAGEMENT.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "designer_calendar")
@ToString
public class DesignerCalendarEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 이벤트 ID

    @Column
    private String title; // 이벤트 제목
    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;
    @Column
    private boolean allDay; // 종일 이벤트 여부
    @Column
    private String eventType; // 이벤트 유형

    private String name;
    private String whoAmI;
}
