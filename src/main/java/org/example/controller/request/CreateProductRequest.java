package org.example.controller.request;

import lombok.Data;
import org.example.validators.ClientContactValidation;

import javax.validation.constraints.NotEmpty;

@Data
@ClientContactValidation
public class CreateProductRequest {
    @NotEmpty
    private String name;
    private Long category;
}
