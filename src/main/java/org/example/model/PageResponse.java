package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<E> {

    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private List<E> elements;
}
