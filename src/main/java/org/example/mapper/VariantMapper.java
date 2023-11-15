package org.example.mapper;

import org.example.controller.request.CreateProductRequest;
import org.example.controller.request.CreateVariantRequest;
import org.example.controller.request.UpdateVariantRequest;
import org.example.dto.ProductDto;
import org.example.dto.VariantDto;
import org.example.entity.ProductEntity;
import org.example.entity.VariantEntity;
import org.example.model.Money;
import org.example.model.Product;
import org.example.model.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VariantMapper {


    VariantDto toDto(Variant model);

    Variant toModel(VariantEntity entity);

    VariantEntity toEntity(Variant model);

    List<VariantDto> toDto(List<Variant> model);

    List<Variant> toModel(List<VariantEntity> entities);

    Variant merge(@MappingTarget Variant target, UpdateVariantRequest request);

    Variant fromRequest(CreateVariantRequest request);
}
