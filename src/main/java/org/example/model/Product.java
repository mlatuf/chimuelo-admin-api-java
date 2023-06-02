package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;
import org.example.dto.VariantDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements HasId {
    private Long id;
    private String name;
    private Category category;
    @Builder.Default
    private List<Variant> variants = new ArrayList<>();
    //FIXEME no va a ser obligatorio esto, es para importar
    private String idEmpretienda;

    public Product addVariant(Variant variant) {
        variants.add(variant);
        return this;
    }
}
