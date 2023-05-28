package org.example.dto;

import org.example.model.Category;
import org.example.model.Money;

import java.util.List;

public class ProductDto {
    private String name;
    private List<VariantDto> variants;
    private Category category;
}
