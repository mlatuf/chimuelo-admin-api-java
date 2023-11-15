package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.*;
import org.example.controller.response.ListProductResponse;
import org.example.dto.ProductDto;
import org.example.mapper.ProductMapper;
import org.example.model.PageResponse;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/products/{productId}/variants")
@RequiredArgsConstructor
public class VariantController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Agregar una variante a un producto")
    @PostMapping
    public ProductDto addVariant(@RequestBody @Validated CreateVariantRequest request, @PathVariable Long productId) {
        return productMapper.toDto(productService.addVariant(productId, request));
    }

    @PatchMapping("/{variantId}")
    @Operation(summary = "Actualiza una variante")
    public ProductDto updateVariant(@RequestBody @Validated UpdateVariantRequest request,
                                    @PathVariable Long productId,
                                    @PathVariable Long variantId) {
        return productMapper.toDto(productService.updateVariant(productId, variantId, request));
    }
}
