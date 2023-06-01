package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.CreateVariantRequest;
import org.example.entity.utils.HashMapConverter;
import org.example.entity.utils.MoneyConverter;
import org.example.mapper.ProductMapper;
import org.example.mapper.VariantMapper;
import org.example.model.Money;
import org.example.model.Product;
import org.example.model.Variant;
import org.example.repository.ProductRepository;
import org.example.repository.VariantRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VariantService {


    private final VariantMapper mapper;
    private final VariantRepository repository;
    private final ProductService productService;



//    private Long id;
//    private String sku;
//    private String name;
//    private String size;
//    private Long stock;
//    @Convert(converter = MoneyConverter.class)
//    @Column(columnDefinition = "TEXT")
//    private Money price;
//    @Convert(converter = HashMapConverter.class)
//    @Column(columnDefinition = "TEXT")
//    private Map<String, String> properties;


}
