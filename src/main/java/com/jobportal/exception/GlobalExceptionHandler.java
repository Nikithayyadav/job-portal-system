package com.jobportal.exception;

import com.jobportal.response.ApiResponse;
import com.jobportal.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CandidateAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleCandidateAlreadyExists(
            CandidateAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(
                        false,
                        null,
                        new ErrorResponse(ex.getMessage()),
                        null
                ));
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleCompanyAlreadyExists(
            CompanyAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(
                        false,
                        null,
                        new ErrorResponse(ex.getMessage()),
                        null
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(
                        false,
                        null,
                        new ErrorResponse(message),
                        null
                ));
    }
}