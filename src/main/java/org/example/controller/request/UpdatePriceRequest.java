package org.example.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdatePriceRequest {
    private BigDecimal percentage;
}
