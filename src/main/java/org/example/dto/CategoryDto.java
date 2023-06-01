package org.example.dto;

import lombok.Data;
import org.example.capabilities.HasId;

@Data
public class CategoryDto  implements HasId {

    private Long id;
    private String name;
    private Long parent;
    //TODO imprime Padre > Padre > yo mismo
    private String fullName;
}
