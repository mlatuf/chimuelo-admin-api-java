package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Client {
    private Long id;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private String instagram;
    private Integer score;
}
