package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateUpdateProductRequest;
import org.example.controller.request.CreateVariantRequest;
import org.example.controller.request.UpdatePriceRequest;
import org.example.entity.ProductEntity;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.ProductMapper;
import org.example.mapper.VariantMapper;
import org.example.model.Money;
import org.example.model.PageResponse;
import org.example.model.Product;
import org.example.model.Variant;
import org.example.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final VariantMapper variantMapper;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    public Product createProduct(CreateUpdateProductRequest request) {
        return this.save(buildProduct(request));
    }

    public Product addVariant(Long productId, CreateVariantRequest request) {
        Variant variant = Variant.builder()
                .sku(request.getSku())
                .name(request.getName())
                .size(request.getSize())
                .stock(request.getStock())
                .price(request.getPrice())
                .properties(request.getProperties())
                .build();

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

    public Product patchProduct(Long productId, CreateUpdateProductRequest request) {
        return save(mapper.mergeChangesIntoOriginal(this.getProductById(productId), this.buildProduct(request)));
    }

    public Product updateVariant(Long productId, Long variantId, CreateVariantRequest request) {
        Product productById = this.getProductById(productId);
        Variant variant = productById.getVariants().stream()
                .filter(var -> var.getId().equals(variantId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(variantId));
        Variant changes = buildVariant(request);
        variantMapper.mergeChangesIntoOriginal(variant, changes);
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

    private Product save(Product product) {
        return mapper.toModel(repository.save(mapper.toEntity(product)));
    }

    private Product buildProduct(CreateUpdateProductRequest request) {
        Product.ProductBuilder builder = Product.builder()
                .name(request.getName());
        if (request.getCategoryId() != null) {
            builder.category(categoryService.getCategoryById(request.getCategoryId()));
        }
        return builder.build();
    }


    private Variant buildVariant(CreateVariantRequest request) {
        return Variant.builder()
                .sku(request.getSku() == null ? request.getSku() : null)
                .name(request.getName() == null ? request.getName() : null)
                .size(request.getSize() == null ? request.getSize() : null)
                .stock(request.getStock() == null ? request.getStock() : null)
                .price(request.getPrice() == null ? request.getPrice() : null)
                .properties(request.getProperties() == null ? request.getProperties() : null)
                .build();
    }

}
