package org.example.entity.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Money;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class MoneyConverter implements AttributeConverter<Money, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Money customerInfo) {

        String money = null;
        try {
            money = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return money;
    }

    @Override
    public Money convertToEntityAttribute(String customerInfoJSON) {

        Money money = null;
        try {
            money = objectMapper.readValue(customerInfoJSON, Money.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return money;
    }
}
