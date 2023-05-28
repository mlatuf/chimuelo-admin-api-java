package org.example.dto;

import lombok.Data;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private Long parent;
    //TODO imprime Padre > Padre > yo mismo
    private String fullName;
}
