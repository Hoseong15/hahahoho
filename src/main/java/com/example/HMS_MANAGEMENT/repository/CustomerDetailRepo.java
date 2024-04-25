package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDetailRepo extends JpaRepository<CustomerDetailEntity , Long> {

    List<CustomerDetailEntity> findByCustomerId(Long customerId);
    List<CustomerDetailEntity> findByVisitBetween(LocalDate start , LocalDate end , Sort visit);

    @Query("select sum(c.cost) from CustomerDetailEntity c where c.visit = :d")
    Integer totalCustomCost(LocalDate d);
}