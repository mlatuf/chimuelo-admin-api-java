package org.example.dto;

import org.example.model.Money;

import java.util.Map;

public class VariantDto {
    private Long id;
    private String sku;
    private String name;
    private String size;
    private Long stock;
    private Money price;
    private Map<String, String> properties;
}
