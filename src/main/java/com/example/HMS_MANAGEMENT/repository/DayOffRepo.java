package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.dto.DesignerDto;
import com.example.HMS_MANAGEMENT.entity.DayOffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOffRepo extends JpaRepository<DayOffEntity,Long> {
    List<DayOffEntity> findByDesignerId(Long designerId);
}
