package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateCategoryRequest;
import org.example.controller.request.UpdateParentRequest;
import org.example.dto.CategoryDto;
import org.example.mapper.CategoryMapper;
import org.example.model.Category;
import org.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    public Map<Long, CategoryDto> findAllCategories() {
        return service.listCategories().stream().collect(Collectors.toMap(Category::getId, mapper::toDto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        return mapper.toDto(service.createCategory(request));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        service.removeCategory(id);
    }

    @PatchMapping("/{id}")
    public CategoryDto patchCategory(@PathVariable Long id,
                                     @RequestBody @Valid UpdateParentRequest request) {
        return mapper.toDto(service.patchParent(id, request.getParent()));
    }

}
