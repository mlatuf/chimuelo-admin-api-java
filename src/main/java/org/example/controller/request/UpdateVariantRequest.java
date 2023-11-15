package org.example.controller.request;

import lombok.Data;
import org.example.model.Money;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
public class UpdateVariantRequest {
    private String sku;
    private String name;
    private Long stock;
    @Valid
    private Money price;
    @Size(max = 3)
    private Map<String, String> properties;
    private String idStock;
}
