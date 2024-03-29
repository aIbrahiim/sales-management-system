package com.example.sales.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    private String transactionType;
    private BigDecimal amount;
}
