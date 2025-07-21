package com.duong.ss08_theories.controller.advice;

import com.duong.ss08_theories.model.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(
                e -> errors.put(e.getField(), e.getDefaultMessage())
        );

        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage("Validation failed");
        response.setData(errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<String>> handleNoSuchElement(NoSuchElementException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage("Resource not found");
        response.setData(null);
        response.setMeta(null);
        response.setError("Account with given ID not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
