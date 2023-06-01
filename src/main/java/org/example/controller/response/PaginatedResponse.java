package org.example.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedResponse<E> {

    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private List<E> elements;
}
