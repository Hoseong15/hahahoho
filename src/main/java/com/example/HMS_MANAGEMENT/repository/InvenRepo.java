package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvenRepo extends JpaRepository<InvenEntity,Long> {

    List<InvenEntity> findAllByInvenStatusOrderByTimeDesc(InvenStatus invenStatus);
    List<InvenEntity> findByDateBetween(LocalDate start , LocalDate end , Sort date);


    @Query("select sum(c.sellCash) from InvenEntity c where c.date = :d")
    Integer totalSellProduct(LocalDate d);

    @Query("select sum(c.buyCash) from InvenEntity c where c.date = :d")
    Integer totalBuyProduct(LocalDate d );
}