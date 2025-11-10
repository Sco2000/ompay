package com.example.ompay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean status;  // "success" ou "error"
    private String message; // message descriptif
    private T data;         // données (optionnelles)
}
