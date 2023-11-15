package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ListProductRequest extends PaginatedRequest {
    @NotNull
    private Long category;
}
