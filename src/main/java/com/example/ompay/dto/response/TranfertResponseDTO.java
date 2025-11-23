package com.example.ompay.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class TranfertResponseDTO {
    private UUID id;
    private Double montant;
    private Double frais;
    private String montantTotal; // Changé en String pour inclure le signe +/- et le format
    private String type;
    private LocalDateTime dateCreation;
    private String destinataire;
}
