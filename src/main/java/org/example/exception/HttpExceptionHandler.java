package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class HttpExceptionHandler {

    public static final String DETAIL_KEY_ID = "id";
    public static final String DETAIL_KEY_REQUEST = "request";

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.CANNOT_USE_EMAIL)
                        .build());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.RESOURCE_NOT_FOUND)
                        .detail(DETAIL_KEY_ID, exception.getId().toString())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse.ErrorResponseBuilder errorBuilder = ErrorResponse.builder()
                .code(ErrorCode.INVALID_REQUEST);
        exception.getBindingResult().getAllErrors().forEach(error ->
                {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        errorBuilder.detail(fieldError.getField(), error.getDefaultMessage());
                    } else
                        errorBuilder.detail(DETAIL_KEY_REQUEST, error.getDefaultMessage());
                }
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBuilder.build());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindException(BindException exception) {
        ErrorResponse.ErrorResponseBuilder errorBuilder = ErrorResponse.builder()
                .code(ErrorCode.INVALID_REQUEST);
        exception.getBindingResult().getAllErrors().forEach(error ->
                {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        errorBuilder.detail(fieldError.getField(), error.getDefaultMessage());
                    } else
                        errorBuilder.detail(DETAIL_KEY_REQUEST, error.getDefaultMessage());
                }
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBuilder.build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericHandler(Exception exception) {
        log.error("Unexpected exception. Please add handler", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.UNEXPECTED_ERROR)
                        .build());
    }

    @ExceptionHandler(CannotDeleteException.class)
    public ResponseEntity<ErrorResponse> cannotDeleteHandler(CannotDeleteException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.CANNOT_DELETE)
                        .message(exception.getMessage())
                        .build());
    }
}
