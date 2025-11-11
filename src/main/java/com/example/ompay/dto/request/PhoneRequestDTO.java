package com.example.ompay.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PhoneRequestDTO {
    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String telephone;

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}
