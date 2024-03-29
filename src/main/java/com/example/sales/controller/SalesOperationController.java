package com.example.sales.controller;

import com.example.sales.model.SalesOperation;
import com.example.sales.service.SalesOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salesOperations")
public class SalesOperationController extends AbstractController {

    @Autowired
    private SalesOperationService salesOperationService;

    @GetMapping
    public List<SalesOperation> getAllSalesOperations() {
        return salesOperationService.getAllSalesOperations();
    }
}
