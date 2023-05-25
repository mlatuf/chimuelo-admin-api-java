package org.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateCategoryRequest {

    @NotEmpty
    private String name;
    private Long parent;
}
