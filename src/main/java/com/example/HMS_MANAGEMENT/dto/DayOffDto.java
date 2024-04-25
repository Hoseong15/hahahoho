package com.example.HMS_MANAGEMENT.dto;

import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DayOffDto {

    private Long id;

    private DesignerEntity designer;

    private String day;
}