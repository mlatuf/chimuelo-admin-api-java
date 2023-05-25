package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateClientRequest;
import org.example.entity.ClientEntity;
import org.example.mapper.ClientMapper;
import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;


    public Client createClient(CreateClientRequest req) {
        ClientEntity build = ClientEntity.builder()
                .name(req.getName())
                .address(req.getAddress())
                .instagram(req.getInstagram())
                .lastname(req.getLastname())
                .phone(req.getPhone())
                .score(0).build();
        return mapper.toModel(repository.save(build));
    }

    public List<Client> findClientByString(String value) {
        return mapper.toModel(repository.findByString(value));
    }


}
