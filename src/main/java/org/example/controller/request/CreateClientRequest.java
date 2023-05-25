package org.example.controller.request;

import lombok.Data;
import org.example.validators.ClientContactValidation;

import javax.validation.constraints.NotEmpty;

@Data
@ClientContactValidation
public class CreateClientRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String address;
    private String phone;
    private String instagram;
}
