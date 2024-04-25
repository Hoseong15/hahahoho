package com.example.HMS_MANAGEMENT.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class DesignerCalendarDto {

    private Long id; // 이벤트 ID
    private String title; // 이벤트 제목
    private LocalDateTime start; // 이벤트 시작 시간
    private LocalDateTime end; // 이벤트 종료 시간
    private boolean allDay; // 종일 이벤트 여부
    private String eventType; // 이벤트 유형
}
