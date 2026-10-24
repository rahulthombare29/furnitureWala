package com.furniturewala.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ApiError error(HttpStatus s, String msg, String path, Map<String, String> fields) {
        return new ApiError(Instant.now(), s.value(), s.getReasonPhrase(), msg, path, fields);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> notFound(ResourceNotFoundException e, HttpServletRequest r) {
        return ResponseEntity.status(404).body(error(HttpStatus.NOT_FOUND, e.getMessage(), r.getRequestURI(), Map.of()));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> duplicate(DuplicateResourceException e, HttpServletRequest r) {
        return ResponseEntity.status(409).body(error(HttpStatus.CONFLICT, e.getMessage(), r.getRequestURI(), Map.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalid(MethodArgumentNotValidException e, HttpServletRequest r) {
        Map<String, String> f = e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(x -> x.getField(), x -> Objects.toString(x.getDefaultMessage(), "Invalid value"), (a, b) -> a));
        return ResponseEntity.badRequest().body(error(HttpStatus.BAD_REQUEST, "Validation failed", r.getRequestURI(), f));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> unreadable(HttpMessageNotReadableException e, HttpServletRequest r) {
        return ResponseEntity.badRequest().body(error(HttpStatus.BAD_REQUEST, "Malformed request or invalid enum value", r.getRequestURI(), Map.of()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> conflict(DataIntegrityViolationException e, HttpServletRequest r) {
        return ResponseEntity.status(409).body(error(HttpStatus.CONFLICT, "Request conflicts with existing data or database constraints", r.getRequestURI(), Map.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> unexpected(Exception e, HttpServletRequest r) {
        return ResponseEntity.status(500).body(error(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", r.getRequestURI(), Map.of()));
    }
}
