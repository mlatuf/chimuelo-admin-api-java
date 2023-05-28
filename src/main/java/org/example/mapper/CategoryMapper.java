package org.example.mapper;

import org.example.dto.CategoryDto;
import org.example.entity.CategoryEntity;
import org.example.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    String CATEGORY_FULLNAME_DELIMITER = " > ";

    @Mappings({
            @Mapping(target = "parent", source = "parent.id"),
            @Mapping(target = "fullName", source = ".", qualifiedByName = "getFullName")
    })
    CategoryDto toDto(Category model);

    Category toModel(CategoryEntity entity);

    CategoryEntity toEntity(Category model);

    List<CategoryDto> toDto(List<Category> entities);

    @Named("getFullName")
    default String getFullName(Category category) {
        Category currentCat = category;
        List<String> parents = new ArrayList<>();
        while (currentCat.getParent() != null) {
            parents.add(currentCat.getParent().getName());
            currentCat = currentCat.getParent();
        }
        if (parents.isEmpty()) {
            return category.getName();
        }
        return parents.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.joining(CATEGORY_FULLNAME_DELIMITER))
                .concat(CATEGORY_FULLNAME_DELIMITER)
                .concat(category.getName());
    }
}
