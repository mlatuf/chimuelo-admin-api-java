package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Crea productos")
    public ProductDto createProduct(@RequestBody @Validated CreateUpdateProductRequest request) {
        return productMapper.toDto(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product")
    public ProductDto getProduct(@PathVariable Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @GetMapping
    @Operation(summary = "Listar productos")
    public ListProductResponse listProductsBy(@Validated ListProductRequest request) {
        PageResponse<Product> page = productService.findBy(request.getCategory(), request.getPageSize(), request.getPageNumber());
        return new ListProductResponse(page.getPageSize(), page.getPageNumber(),
                page.getTotalElements(), page.getTotalPages(), productMapper.toDto(page.getElements()));
    }

    @Operation(summary = "Actualizar producto")
    @PatchMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody @Validated CreateUpdateProductRequest request) {
        return productMapper.toDto(productService.patchProduct(productId, request));
    }

    @Operation(summary = "Agregar una variante a un producto")
    @PostMapping("/{productId}/variants")
    public ProductDto addVariant(@RequestBody @Validated CreateVariantRequest request, @PathVariable Long productId) {
        return productMapper.toDto(productService.addVariant(productId, request));
    }

    @PatchMapping("/{productId}/variants/{variantId}")
    @Operation(summary = "Actualiza una variante")
    public ProductDto updateVariant(@RequestBody @Validated CreateVariantRequest request,
                                    @PathVariable Long productId,
                                    @PathVariable Long variantId) {
        return productMapper.toDto(productService.updateVariant(productId, variantId, request));
    }

    @PatchMapping("/{productId}/price")
    @Operation(summary = "Actualiza el precio de todas las variantes del producto")
    public ProductDto updateVariants(@RequestBody @Validated UpdatePriceRequest request,
                                     @PathVariable Long productId) {
        return productMapper.toDto(productService.updateProductPrice(productId, request));
    }
}
