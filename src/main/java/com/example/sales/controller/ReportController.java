package com.example.sales.controller;


import com.example.sales.report.ClientReportService;
import com.example.sales.report.SalesOperationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/reports")
public class ReportController extends AbstractController{

    @Autowired
    private SalesOperationReportService salesOperationReportService;

    @Autowired
    private ClientReportService clientReportService;

    @GetMapping("/sales")
    public ResponseEntity<byte[]> downloadSalesReport(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws IOException {

        byte[] csvReport = salesOperationReportService.generateSalesReport(startDate, endDate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "sales-report.csv");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvReport);
    }


    @GetMapping("/client")
    public ResponseEntity<byte[]> downloadClientReport() throws IOException {
        byte[] csvReport = clientReportService.generateClientReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "client-report.csv");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok().headers(headers).body(csvReport);
    }
}
