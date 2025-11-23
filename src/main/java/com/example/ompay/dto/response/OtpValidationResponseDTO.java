package com.example.ompay.dto.response;
import lombok.Data;

@Data
public class OtpValidationResponseDTO {

    private String telephone;
    private String message;

    public OtpValidationResponseDTO() {}

    public OtpValidationResponseDTO(String telephone, String message) {
        this.telephone = telephone;
        this.message = message;
    }
}
