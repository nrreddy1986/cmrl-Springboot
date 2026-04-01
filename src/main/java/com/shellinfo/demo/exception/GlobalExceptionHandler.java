package com.shellinfo.demo.exception;

import com.shellinfo.demo.model.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure("Validation Failed", errors));
    }

    // ✅ Custom
    @ExceptionHandler(MobileAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleMobileExists(MobileAlreadyExistsException ex) {

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure("Registration Failed", ex.getMessage()));
    }

    // 🔥 JWT EXPIRED (VERY IMPORTANT)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<Object>> handleJwtExpired(ExpiredJwtException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("token", "JWT expired");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.failure("Token Expired", errors));
    }

    // 🔥 CUSTOM TOKEN EXCEPTION
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiResponse<Object>> handleToken(TokenExpiredException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("token", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.failure("Token Expired", errors));
    }

    // ✅ Runtime (General)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure("Request Failed", errors));
    }

    // ✅ Catch All
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure("Internal Server Error", ex.getMessage()));
    }
}