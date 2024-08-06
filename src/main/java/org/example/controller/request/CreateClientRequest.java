package org.example.controller.request;

import lombok.Data;
import org.example.dto.AddressDto;
import org.example.validators.ClientContactValidation;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@ClientContactValidation
public class CreateClientRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    @Valid
    private AddressDto address;
    private String phone;
    private String instagram;
}
