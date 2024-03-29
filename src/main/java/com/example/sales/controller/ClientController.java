package com.example.sales.controller;

import com.example.sales.model.Client;
import com.example.sales.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController extends AbstractController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return handle(clientService.getAllClients());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return handle(clientService.createClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return handle(clientService.updateClient(id, clientDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return handle(clientService.deleteClient(id));
    }
}
