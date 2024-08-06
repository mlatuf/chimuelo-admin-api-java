package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotEmpty
    @NotNull
    private String city;
    @NotEmpty
    @NotNull
    private String street;
    @NotEmpty
    @NotNull
    private String number;
    @NotEmpty
    @NotNull
    private String province;
    private String notes;
    private String flat;
}
