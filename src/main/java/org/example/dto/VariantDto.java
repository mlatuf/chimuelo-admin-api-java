package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;
import org.example.model.Money;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantDto  implements HasId {
    private Long id;
    private String sku;
    private Long stock;
    private Money price;
    private Map<String, String> properties;
    private String idStock;
}
