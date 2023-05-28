package org.example.controller.request;

import lombok.Data;
import org.example.model.Money;
import org.example.validators.ClientContactValidation;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@ClientContactValidation
public class CreateVariantRequest {
    @NotEmpty
    private String sku;
    @NotEmpty
    private String name;
    @NotEmpty
    private String size;
    @NotNull
    private Long stock;
    @Valid
    @NotNull
    private Money price; //FIXME para mi el precio va acá, no en producto, porque puede que una variante salga mas que otra (distinta estampa)
    private Map<String, String> properties;
}
