package com.example.ompay.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class CompteResponseDTO {
    private UUID id;
    private UUID utilisateurId;
    private String telephone;
    private Double solde;
    private String prenom;
    private String nom;
    private List<TranfertResponseDTO> transactions;
}
