package org.example.service;

import org.example.controller.request.*;
import org.example.entity.ProductEntity;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ProductMapper;
import org.example.mapper.VariantMapper;
import org.example.model.*;
import org.example.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class ProductService {

    private final ProductMapper mapper;
    private final VariantMapper variantMapper;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    private final Map<ExportStrategyName, ExportStrategy> exportStrategies;

    public ProductService(ProductMapper mapper,
                          VariantMapper variantMapper,
                          ProductRepository repository,
                          CategoryService categoryService,
                          List<ExportStrategy> exportStrategies) {
        this.mapper = mapper;
        this.variantMapper = variantMapper;
        this.repository = repository;
        this.categoryService = categoryService;
        this.exportStrategies = exportStrategies.stream().collect(Collectors.toMap(ExportStrategy::getStrategyName, Function.identity()));
    }

    public Product createProduct(CreateProductRequest request) {
        return this.save(mapper.fromRequest(request));
    }

    public Product addVariant(Long productId, CreateVariantRequest request) {
        Variant variant = variantMapper.fromRequest(request);

        Product product = this
                .getProductById(productId)
                .addVariant(variant);
        return this.save(product);
    }

    public Product getProductById(Long id) {
        return mapper.toModel(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public PageResponse<Product> findBy(Long categoryId, int pageSize, int pageNumber) {
        Page<ProductEntity> byCategory = null;
        if (categoryId == null) {
            byCategory = repository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        } else {
            byCategory = repository.findByCategory(categoryId, PageRequest.of(pageNumber - 1, pageSize));
        }
        PageResponse<Product> page = PageResponse.<Product>builder()
                .totalPages(byCategory.getTotalPages())
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .totalElements(byCategory.getTotalElements())
                .elements(mapper.toModel(byCategory.get().collect(Collectors.toList()))).build();
        return page;
    }

    public Product patchProduct(Long productId, UpdateProductRequest request) {
        return save(mapper.merge(this.getProductById(productId), request));
    }

    public Product updateVariant(Long productId, Long variantId, UpdateVariantRequest request) {
        Product productById = this.getProductById(productId);
        Variant variant = productById.getVariants().stream()
                .filter(var -> var.getId().equals(variantId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(variantId));
        variantMapper.merge(variant, request);
        return this.save(productById);
    }

    public Product updateProductPrice(Long productId, UpdatePriceRequest request) {
        Product productById = this.getProductById(productId);
        productById.getVariants().forEach(variant -> {
            Money price = variant.getPrice();
            BigDecimal percentage = price.getAmount().multiply(request.getPercentage()).divide(BigDecimal.valueOf(100));
            price.setAmount(price.getAmount().add(percentage));
        });
        return save(productById);
    }

    public Product deleteProduct(Long productId) {
        Product productById = this.getProductById(productId);
        repository.deleteById(productId);
        return productById;
    }

    private Product save(Product product) {
        return mapper.toModel(repository.save(mapper.toEntity(product)));
    }

    public List<Product> getAll() {
        return mapper.toModel(repository.findAll());
    }

    public File export(ExportRequest request) throws IOException {
        ExportStrategyName strategy = request.getStrategy();
        return ofNullable(exportStrategies.get(strategy))
                .orElseThrow(() -> new IllegalArgumentException("missing strategy " + strategy))
                .toFile(this.getAll());
    }
}
