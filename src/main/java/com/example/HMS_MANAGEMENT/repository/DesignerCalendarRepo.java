package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerCalendarRepo extends JpaRepository<DesignerCalendarEntity,Long> {

}