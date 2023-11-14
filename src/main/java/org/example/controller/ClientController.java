package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateClientRequest;
import org.example.dto.ClientDto;
import org.example.mapper.ClientMapper;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Crear cliente")
    public ClientDto createClient(
            @RequestBody @Valid CreateClientRequest request) {
        return mapper.toDto(service.createClient(request));
    }

    @GetMapping
    @Operation(summary = "Busqueda por parametros")
    public List<ClientDto> search(@RequestParam(required = false) @Size(min = 3) String keyword) {
        return mapper.toDto(service.findClientByString(keyword));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borra por id")
    public ClientDto deleteClient(@PathVariable Long id) {
        return mapper.toDto(service.deleteClientById(id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera por id")
    public ClientDto getclient(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizacion de clientes")
    public ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return mapper.toDto(service.updateClient(id, clientDto));
    }
}
