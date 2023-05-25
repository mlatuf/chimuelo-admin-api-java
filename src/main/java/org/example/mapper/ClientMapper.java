package org.example.mapper;

import org.example.dto.ClientDto;
import org.example.entity.ClientEntity;
import org.example.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {


    ClientDto toDto(Client model);

    Client toModel(ClientEntity entity);

    ClientEntity toEntity(Client model);

    List<ClientDto> toDto(List<Client> model);

    List<Client> toModel(List<ClientEntity> entities);

}
