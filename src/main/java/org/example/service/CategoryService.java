package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateCategoryRequest;
import org.example.entity.CategoryEntity;
import org.example.exception.CannotDeleteException;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.CategoryMapper;
import org.example.model.Category;
import org.example.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    public Category createCategory(CreateCategoryRequest categoryRequest) {
        Category.CategoryBuilder builder = Category.builder().name(categoryRequest.getName());
        if (categoryRequest.getParent() != null) {
            builder.parent(this.getCategoryById(categoryRequest.getParent()));
        }
        CategoryEntity categoryEntity = mapper.toEntity(builder.build());

        return mapper.toModel(repository.save(categoryEntity));
    }

    public List<Category> listCategories() {
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }

    public Category getCategoryById(Long id) {
        return mapper.toModel(this.getCategoryEntity(id));
    }

    public void removeCategory(Long id) {
        //TODO check if products have this category
        List<CategoryEntity> byParentId = repository.findByParentId(id);
        if (!byParentId.isEmpty()) {
            throw new CannotDeleteException("Contains references from categories" + byParentId.stream().map(CategoryEntity::getId).collect(Collectors.toList()));
        }
        repository.delete(getCategoryEntity(id));
    }


    public Category patchParent(Long id, Long parent) {
        Assert.isTrue(!id.equals(parent), "Cannot put yourself as your parent");
        CategoryEntity categoryEntity = this.getCategoryEntity(id);
        categoryEntity.setParent(this.getCategoryEntity(parent));
        return mapper.toModel(repository.save(categoryEntity));
    }

    private CategoryEntity getCategoryEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
