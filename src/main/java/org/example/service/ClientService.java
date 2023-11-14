package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateClientRequest;
import org.example.dto.ClientDto;
import org.example.entity.ClientEntity;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ClientMapper;
import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;


    public Client createClient(CreateClientRequest req) {
        Client build = Client.builder()
                .name(req.getName())
                .address(req.getAddress())
                .instagram(req.getInstagram())
                .lastname(req.getLastname())
                .phone(req.getPhone())
                .score(0).build();
        ClientEntity clientEntity = mapper.toEntity(build);
        return mapper.toModel(repository.save(clientEntity));
    }

    public List<Client> findClientByString(String value) {
        if (StringUtils.hasText(value)) {
            return mapper.toModel(repository.findByString(value));
        }
        return mapper.toModel(repository.findAll());
    }


    public Client updateClient(Long id, ClientDto clientDto) {
        Client merge = mapper.merge(getById(id), mapper.toModel(clientDto));
        repository.save(mapper.toEntity(merge));
        return merge;
    }

    public Client deleteClientById(Long id) {
        Client byId = this.getById(id);
        repository.deleteById(id);
        return byId;
    }

    public Client getById(Long id) {
        return mapper.toModel(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }
}
