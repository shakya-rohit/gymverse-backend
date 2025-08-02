package com.gymverse.backend.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND, "Resource Not Found");
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "Bad Request");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "Illegal Argument");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, "Runtime Error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, "Unhandled Exception");
    }

    private ResponseEntity<ApiError> buildErrorResponse(Exception ex, HttpServletRequest request,
                                                        HttpStatus status, String error) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                status.value(),
                error,
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, status);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult()
                           .getFieldErrors()
                           .stream()
                           .map(err -> err.getField() + ": " + err.getDefaultMessage())
                           .collect(Collectors.joining(", "));

        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "Validation Failed: " + message);
    }

}