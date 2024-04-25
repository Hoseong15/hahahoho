package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface DesignerRepo extends JpaRepository<DesignerEntity,Long> {
    DesignerEntity findById(long id);

    List<DesignerEntity> findByDateBetween(LocalDate start , LocalDate end , Sort date);


    @Query("select sum(c.sal) from DesignerEntity c where c.date = :e")
    Integer totalExpense(LocalDate e);

}
