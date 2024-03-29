package com.example.sales.service.impl;

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
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client clientDetails) {
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
        clientRepository.deleteById(id);
        return null;
    }
}
