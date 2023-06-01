package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ListProductRequest extends PaginatedRequest {
    private Long category;
}
