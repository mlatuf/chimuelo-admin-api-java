package org.example.mapper;

import org.example.dto.ProductDto;
import org.example.entity.ProductEntity;
import org.example.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, VariantMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {
    ProductDto toDto(Product model);

    Product toModel(ProductEntity entity);

    ProductEntity toEntity(Product model);

    List<ProductDto> toDto(List<Product> model);

    List<Product> toModel(List<ProductEntity> entities);

    default Product mergeChangesIntoOriginal(Product original, Product changes) {
        return Product.builder()
                .name(changes.getName() == null ? original.getName() : changes.getName())
                .category(changes.getCategory() == null ? original.getCategory() : changes.getCategory())
                .id(changes.getId() == null ? original.getId() : changes.getId())
                .variants(changes.getVariants() == null ? original.getVariants() : changes.getVariants())
                .idEmpretienda(changes.getIdEmpretienda() == null ? original.getIdEmpretienda() : changes.getIdEmpretienda())
                .build();
    }
}
