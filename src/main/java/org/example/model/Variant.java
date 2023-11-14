package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variant implements HasId {
    private Long id;
    private String sku;
    private String size;
    private Long stock;
    private Money price;
    private Map<String, String> properties;
    private String idStock;
}
