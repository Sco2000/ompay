package com.example.ompay.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaiementRequestDTO {

    @NotBlank(message = "Le téléphone ou le code marchand est obligatoire")
    private String identifiantMarchand; // peut être un téléphone ou un code marchand

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double montant;

    public PaiementRequestDTO() {}

    public PaiementRequestDTO(String identifiantMarchand, Double montant) {
        this.identifiantMarchand = identifiantMarchand;
        this.montant = montant;
    }
}
