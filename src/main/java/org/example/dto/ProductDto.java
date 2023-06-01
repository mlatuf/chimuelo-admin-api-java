package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;
import org.example.model.Category;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto  implements HasId {
    private Long id;
    private String name;
    private List<VariantDto> variants;
    private CategoryDto category;
}
