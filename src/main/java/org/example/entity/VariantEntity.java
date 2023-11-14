package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;
import org.example.entity.utils.HashMapConverter;
import org.example.entity.utils.MoneyConverter;
import org.example.model.Money;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "variants")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantEntity implements HasId {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "variant_sequence"
    )
    @SequenceGenerator(
            name = "variant_sequence",
            sequenceName = "variant_sequence",
            allocationSize = 1
    )
    private Long id;
    private String sku;
    private String size;
    private Long stock;
    @Convert(converter = MoneyConverter.class)
    @Column(columnDefinition = "TEXT")
    private Money price;
    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> properties;
    private String idStock;
}
