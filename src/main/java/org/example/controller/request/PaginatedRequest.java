package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class PaginatedRequest {

    @Min(1)
    private int pageSize = 10;
    @Min(1)
    private int pageNumber = 1;
}
