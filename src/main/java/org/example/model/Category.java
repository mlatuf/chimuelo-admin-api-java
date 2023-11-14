package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements HasId {

    private Long id;
    private String name;
    private Category parent;
    private static final String CATEGORY_FULLNAME_DELIMITER = " > ";

    public String getFullName() {
        Category currentCat = this;
        List<String> parents = new ArrayList<>();
        while (currentCat.getParent() != null) {
            parents.add(currentCat.getParent().getName());
            currentCat = currentCat.getParent();
        }
        if (parents.isEmpty()) {
            return this.getName();
        }
        return parents.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.joining(CATEGORY_FULLNAME_DELIMITER))
                .concat(CATEGORY_FULLNAME_DELIMITER)
                .concat(this.getName());
    }
}
