package com.example.ompay.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PinRequestDTO {
    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "Le code PIN est obligatoire")
    private String codeConnexion;

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getCodeConnexion() { return codeConnexion; }
    public void setCodeConnexion(String codeConnexion) { this.codeConnexion = codeConnexion; }
}
