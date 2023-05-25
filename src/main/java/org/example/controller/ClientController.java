package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateClientRequest;
import org.example.dto.ClientDto;
import org.example.mapper.ClientMapper;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    public ClientDto createClient(
            @RequestBody @Valid CreateClientRequest request) {
        return mapper.toDto(service.createClient(request));
    }

    @GetMapping
    public List<ClientDto> createClient(@RequestParam @Size(min = 3) String keyword) {
        return mapper.toDto(service.findClientByString(keyword));
    }
}
