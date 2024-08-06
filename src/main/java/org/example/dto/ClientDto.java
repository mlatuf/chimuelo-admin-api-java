package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.capabilities.HasId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements HasId {
    private Long id;
    private String name;
    private String lastname;
    private AddressDto address;
    private String phone;
    private String instagram;
    private Integer score;
}
