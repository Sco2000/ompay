package com.example.ompay.utils;

import com.example.ompay.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(true, message, data);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return success(message, data, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse<Object>> error(String message, HttpStatus status) {
        ApiResponse<Object> response = new ApiResponse<>(false, message, null);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ApiResponse<Object>> error(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String message, T data, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(false, message, data);
        return new ResponseEntity<>(response, status);
    }
}
