package org.example.exception;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException {
    @Getter
    private final Long id;

    public ResourceNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public ResourceNotFoundException(Long id) {
        this.id = id;
    }
}
