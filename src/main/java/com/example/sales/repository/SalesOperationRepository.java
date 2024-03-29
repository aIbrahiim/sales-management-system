package com.example.sales.repository;

import com.example.sales.model.SalesOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOperationRepository extends JpaRepository<SalesOperation, Long> {
}