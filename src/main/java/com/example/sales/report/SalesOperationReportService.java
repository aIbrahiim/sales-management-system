package com.example.sales.report;

import com.example.sales.repository.SalesOperationRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class SalesOperationReportService {

    @Autowired
    private SalesOperationRepository salesOperationRepository;

    public byte[] generateSalesReport(Date startDate, Date endDate) throws IOException {
        Long totalSales = salesOperationRepository.countByDateRange(startDate, endDate);
        Double totalRevenue = salesOperationRepository.sumTotalByDateRange(startDate, endDate);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(baos, StandardCharsets.UTF_8), CSVFormat.DEFAULT
                .withHeader("Total Sales", "Total Revenue"));

        printer.printRecord(totalSales, totalRevenue);

        printer.flush();
        return baos.toByteArray();
    }
}
