package com.example.ompay.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransfertRequestDTO {

    @NotBlank(message = "Le numéro de téléphone du destinataire est obligatoire")
    private String telephoneDestinataire;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double montant;

    public TransfertRequestDTO() {}

    public TransfertRequestDTO(String telephoneDestinataire, Double montant) {
        this.telephoneDestinataire = telephoneDestinataire;
        this.montant = montant;
    }
}
