package org.example.exception.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.example.exception.ErrorCode;

import java.util.Map;

@Builder
public class ErrorResponse {
    private ErrorCode code;
    private String message;
    @Singular
    @Getter
    private Map<String, String> details;

    public String getCode() {
        return this.code.name();
    }

    public String getMessage() {
        return this.code.getMessage() + ". " + message;
    }


}
