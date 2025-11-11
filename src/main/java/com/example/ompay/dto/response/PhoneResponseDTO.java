package com.example.ompay.dto.response;

public class PhoneResponseDTO {

    private String telephone;
    private String message;

    public PhoneResponseDTO() {}

    public PhoneResponseDTO(String telephone, String message) {
        this.telephone = telephone;
        this.message = message;
    }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
