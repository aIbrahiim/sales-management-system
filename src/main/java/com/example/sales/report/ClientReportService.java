package com.example.sales.report;

import com.example.sales.repository.ClientRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ClientReportService {

    @Autowired
    private ClientRepository clientRepository;

    public byte[] generateClientReport() throws IOException {
        Long totalNumberOfClients = clientRepository.getTotalNumberOfClients();
        List<Object[]> topSpendingClient = clientRepository.getTopSpendingClient();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CSVPrinter printer = new CSVPrinter(new OutputStreamWriter(baos, StandardCharsets.UTF_8), CSVFormat.DEFAULT.withHeader("Report Type", "Data"));

        printer.printRecord("Total Number of Clients", totalNumberOfClients);
        if (!topSpendingClient.isEmpty()) {
            Object[] topClient = topSpendingClient.get(0);
            printer.printRecord("Top Spending Client ID", topClient[0]);
            printer.printRecord("Total Spent", topClient[1]);
        }

        printer.flush();
        return baos.toByteArray();
    }
}
