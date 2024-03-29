package com.example.sales.repository;

import com.example.sales.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

    @Query("SELECT COUNT(c) FROM Client c")
    Long getTotalNumberOfClients();

    @Query(value = "SELECT c.id, SUM(o.total) as totalSpent FROM Client c JOIN Order o ON c.id = o.client.id GROUP BY c.id ORDER BY totalSpent DESC LIMIT 1", nativeQuery = true)
    List<Object[]> getTopSpendingClient();
}
