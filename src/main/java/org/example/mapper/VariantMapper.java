package org.example.mapper;

import org.example.dto.ProductDto;
import org.example.dto.VariantDto;
import org.example.entity.ProductEntity;
import org.example.entity.VariantEntity;
import org.example.model.Money;
import org.example.model.Product;
import org.example.model.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VariantMapper {


    VariantDto toDto(Variant model);

    Variant toModel(VariantEntity entity);

    VariantEntity toEntity(Variant model);

    List<VariantDto> toDto(List<Variant> model);

    List<Variant> toModel(List<VariantEntity> entities);


    default Variant mergeChangesIntoOriginal(Variant original, Variant changes) {
        return Variant.builder()
                .sku(changes.getSku() == null ? original.getSku() : changes.getSku())
                .size(changes.getSize() == null ? original.getSize() : changes.getSize())
                .stock(changes.getStock() == null ? original.getStock() : changes.getStock())
                .price(changes.getPrice() == null ? original.getPrice() : changes.getPrice())
                .properties(changes.getProperties() == null ? original.getProperties() : changes.getProperties())
                .idStock(changes.getIdStock() == null ? original.getIdStock() : changes.getIdStock())
                .build();
    }
}
