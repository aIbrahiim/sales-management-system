package com.example.sales.repository;

import com.example.sales.model.SalesOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SalesOperationRepository extends JpaRepository<SalesOperation, Long> {

    @Query("SELECT COUNT(s) FROM SalesOperation s WHERE s.creationDate BETWEEN :startDate AND :endDate")
    Long countByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(s.total) FROM SalesOperation s WHERE s.creationDate BETWEEN :startDate AND :endDate")
    Double sumTotalByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
