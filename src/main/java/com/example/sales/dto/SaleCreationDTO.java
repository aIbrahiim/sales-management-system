package com.example.sales.dto;


import lombok.Data;

import java.util.List;

@Data
public class SaleCreationDTO {
    private Long clientId;
    private Long sellerId;
    private List<TransactionDTO> transactions;
}
