package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    //페이징
    Page<CustomerEntity> findAll(Pageable pageable);

//    List<CustomerEntity> findByFirstVisitBetween(LocalDate start , LocalDate end , Sort firstVisit);

}