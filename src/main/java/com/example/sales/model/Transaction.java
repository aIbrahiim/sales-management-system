package com.example.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name = "Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private Long transactionID;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private SalesOperation salesOrder;

    @Column(name = "TransactionType")
    private String transactionType;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "TransactionDate")
    private Date transactionDate;

}