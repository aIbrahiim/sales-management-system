package com.example.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name = "Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private SalesOperation salesOrder;

    @Column(name = "TransactionType")
    private String transactionType;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "TransactionDate")
    private Date transactionDate;

    @Column(name = "creation_date", nullable = true)
    private Date creationDate;

    @Column(name = "last_modified_date", nullable = true)
    private Date lastModifiedDate;

    @PrePersist
    public void onPrePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.lastModifiedDate = new Date();
    }

    @PreRemove
    public void onPreRemove() {
        log.info("Preparing to delete entity with id {}", this.id);
    }

}