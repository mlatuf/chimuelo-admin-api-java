package org.example.controller.request;

import lombok.Data;
import org.example.model.Money;
import org.example.validators.ClientContactValidation;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
public class CreateVariantRequest {

    @NotEmpty
    private String sku;
    @NotEmpty
    private String name;
    @NotNull
    private Long stock;
    @Valid
    @NotNull
    private Money price;
    @Size(max = 3)
    private Map<String, String> properties;

    private String idStock;
}
