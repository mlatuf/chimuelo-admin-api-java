package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUpdateProductRequest {
    @NotEmpty
    private String name;
    private Long categoryId;
}
