package com.example.ompay.dto.response;
import lombok.Data;

@Data
public class PhoneResponseDTO {

    private String telephone;
    private String message;

    public PhoneResponseDTO() {}

    public PhoneResponseDTO(String telephone, String message) {
        this.telephone = telephone;
        this.message = message;
    }
}
