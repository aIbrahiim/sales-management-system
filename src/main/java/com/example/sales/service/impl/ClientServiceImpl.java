package com.example.sales.service.impl;

import com.example.sales.exception.ClientNotFoundException;
import com.example.sales.model.Client;
import com.example.sales.repository.ClientRepository;
import com.example.sales.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        log.info("Fetching client with ID: {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Client not found with id: {}", id);
                    throw new ClientNotFoundException("Client not found with id: " + id);
                });
    }

    @Override
    public Client createClient(Client client) {
        log.info("Creating client: {}", client.getName());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client clientDetails) {
        log.info("Updating client with ID: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setName(clientDetails.getName());
        client.setLastName(clientDetails.getLastName());
        client.setMobile(clientDetails.getMobile());
        client.setEmail(clientDetails.getEmail());
        client.setAddress(clientDetails.getAddress());
        return clientRepository.save(client);
    }

    @Override
    public Client deleteClient(Long id) {
        log.info("Deleting client with ID: {}", id);
        clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error: No client found with ID: {}", id);
                    return new ClientNotFoundException("No client found with ID: " + id);
                });
        clientRepository.deleteById(id);
        log.info("Client deleted successfully with ID: {}", id);
        return Client.builder()
                .id(id)
                .build();
    }
}
