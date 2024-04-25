package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
    List<SalesEntity> findByType(String type);
}