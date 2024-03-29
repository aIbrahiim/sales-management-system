package com.example.sales.service.impl;

import com.example.sales.dto.SaleCreationDTO;
import com.example.sales.exception.ClientNotFoundException;
import com.example.sales.exception.SellerNotFoundException;
import com.example.sales.model.Client;
import com.example.sales.model.SalesOperation;
import com.example.sales.model.Seller;
import com.example.sales.model.Transaction;
import com.example.sales.repository.ClientRepository;
import com.example.sales.repository.SalesOperationRepository;
import com.example.sales.repository.SellerRepository;
import com.example.sales.repository.TransactionRepository;
import com.example.sales.service.SalesOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SalesOperationServiceImpl implements SalesOperationService {

    @Autowired
    private SalesOperationRepository salesOperationRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public List<SalesOperation> getAllSalesOperations() {
        return salesOperationRepository.findAll();
    }

    public SalesOperation createSale(SaleCreationDTO saleDTO) {
        log.info("Creating a new sales operation for client ID: {} and seller ID: {}", saleDTO.getClientId(), saleDTO.getSellerId());

        Client client = clientRepository.findById(saleDTO.getClientId())
                .orElseThrow(() -> {
                    log.error("Client with ID: {} not found", saleDTO.getClientId());
                    throw new ClientNotFoundException("Client not found");
                });
        Seller seller = sellerRepository.findById(saleDTO.getSellerId())
                .orElseThrow(() -> {
                            log.error("Seller with ID: {} not found", saleDTO.getSellerId());
                            throw new SellerNotFoundException("Seller not found");
                });

        SalesOperation sale = new SalesOperation();
        sale.setClient(client);
        sale.setSeller(seller);
        sale.setCreationDate(new Date());

        final SalesOperation savedSale = salesOperationRepository.save(sale);
        log.info("Sales operation created with ID: {}", savedSale.getId());

        double total = saleDTO.getTransactions().stream()
                .mapToDouble(transactionDTO -> {
                    Transaction transaction = new Transaction();
                    transaction.setSalesOrder(savedSale);
                    transaction.setTransactionType(transactionDTO.getTransactionType());
                    transaction.setAmount(transactionDTO.getAmount());
                    transaction.setTransactionDate(new Date());
                    transactionRepository.save(transaction);
                    return transactionDTO.getAmount().doubleValue();
                }).sum();

        savedSale.setTotal(total);

        SalesOperation salesOperation = salesOperationRepository.save(savedSale);
        log.info("Sales operation with ID: {} updated with total amount: {}", salesOperation.getId(), salesOperation.getTotal());
        return salesOperation;
    }
}
