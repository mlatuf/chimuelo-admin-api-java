package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateUpdateProductRequest;
import org.example.controller.request.CreateVariantRequest;
import org.example.controller.request.ListProductRequest;
import org.example.controller.request.UpdatePriceRequest;
import org.example.controller.response.ListProductResponse;
import org.example.dto.ProductDto;
import org.example.mapper.ProductMapper;
import org.example.model.PageResponse;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ProductDto createProduct(@RequestBody @Validated CreateUpdateProductRequest request) {
        return productMapper.toDto(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ProductDto createProduct(@PathVariable Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @GetMapping
    public ListProductResponse listProductsBy(@Validated ListProductRequest request) {
        PageResponse<Product> page = productService.findBy(request.getCategory(), request.getPageSize(), request.getPageNumber());
        return new ListProductResponse(page.getPageSize(), page.getPageNumber(),
                page.getTotalElements(), page.getTotalPages(), productMapper.toDto(page.getElements()));
    }

    @PatchMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody @Validated CreateUpdateProductRequest request) {
        return productMapper.toDto(productService.patchProduct(productId, request));
    }

    @PostMapping("/{productId}/variants")
    public ProductDto addVariant(@RequestBody @Validated CreateVariantRequest request, @PathVariable Long productId) {
        return productMapper.toDto(productService.addVariant(productId, request));
    }

    @PostMapping("/{productId}/variants/{variantId}")
    public ProductDto updateVariant(@RequestBody @Validated CreateVariantRequest request,
                                    @PathVariable Long productId,
                                    @PathVariable Long variantId) {
        return productMapper.toDto(productService.updateVariant(productId, variantId, request));
    }

    @PatchMapping("/{productId}/price")
    public ProductDto updateVariants(@RequestBody @Validated UpdatePriceRequest request,
                                     @PathVariable Long productId) {
        return productMapper.toDto(productService.updateProductPrice(productId, request));
    }
}
