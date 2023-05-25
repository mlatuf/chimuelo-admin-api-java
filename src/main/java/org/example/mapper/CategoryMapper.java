package org.example.mapper;

import org.example.dto.CategoryDto;
import org.example.entity.CategoryEntity;
import org.example.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mappings({
            @Mapping(target = "parent", source = "parent.id")
    })
    CategoryDto toDto(Category model);

    Category toModel(CategoryEntity entity);

    CategoryEntity toEntity(Category model);

    List<CategoryDto> toDto(List<Category> entities);
}
