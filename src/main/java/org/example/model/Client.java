package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client  implements HasId {
    private Long id;
    private String name;
    private String lastname;
    private Address address;
    private String phone;
    private String instagram;
    private Integer score;
}
