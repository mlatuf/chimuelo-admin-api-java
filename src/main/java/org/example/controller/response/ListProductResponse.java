package org.example.controller.response;

import lombok.Getter;
import org.example.dto.ProductDto;
import org.example.dto.VariantDto;
import org.example.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ListProductResponse extends PaginatedResponse<ProductDto> {

    private Map<String, Set<String>> propertiesSummary;

    public ListProductResponse(int pageSize, int pageNumber, long totalElements, int totalPages, List<ProductDto> elements) {
        super(pageSize, pageNumber, totalElements, totalPages, elements);


        propertiesSummary = elements.stream()
                .map(ProductDto::getVariants)
                .flatMap(List::stream)
                .map(VariantDto::getProperties)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(entry -> entry.getKey(),
                        Collectors.mapping(entry -> entry.getValue(),
                                Collectors.toSet())));
    }
}
