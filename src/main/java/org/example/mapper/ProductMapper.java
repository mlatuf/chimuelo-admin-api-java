package org.example.mapper;

import org.example.controller.request.CreateProductRequest;
import org.example.controller.request.UpdateProductRequest;
import org.example.dto.ProductDto;
import org.example.entity.ProductEntity;
import org.example.model.Client;
import org.example.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, VariantMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    ProductDto toDto(Product model);

    Product toModel(ProductEntity entity);

    ProductEntity toEntity(Product model);

    List<ProductDto> toDto(List<Product> model);

    List<Product> toModel(List<ProductEntity> entities);

    Product merge(@MappingTarget Product target, UpdateProductRequest changes);

    Product fromRequest(CreateProductRequest request);
}
