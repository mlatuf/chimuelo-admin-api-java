package org.example.validators;

import org.example.controller.request.CreateClientRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientContactValidator implements ConstraintValidator<ClientContactValidation, CreateClientRequest> {

    public boolean isValid(CreateClientRequest req, ConstraintValidatorContext cxt) {
        return req.getInstagram() != null || req.getPhone() != null;
    }
}
