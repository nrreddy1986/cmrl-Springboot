package com.shellinfo.demo.exception;

import com.shellinfo.demo.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure("Validation Failed", errors));
    }

    // Handle Custom Exceptions
    @ExceptionHandler(MobileAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleMobileExists(MobileAlreadyExistsException ex) {

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure("Registration Failed", ex.getMessage()));
    }

    // Handle Runtime Exceptions (Login, Verify etc.)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex) {

        Map<String, String> errors = new HashMap<>();

        errors.put("mobileNumber", ex.getMessage());

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure("Login Failed", errors));
    }

    // Catch All (Very Important)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure("Internal Server Error", ex.getMessage()));
    }
}


