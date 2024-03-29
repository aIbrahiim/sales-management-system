package com.example.sales.service;

import com.example.sales.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    Client createClient(Client client);


    Client updateClient(Long id, Client clientDetails);

    Client deleteClient(Long id);
}
