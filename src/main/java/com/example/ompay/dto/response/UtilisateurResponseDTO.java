package com.example.ompay.dto.response;

import java.util.UUID;
import lombok.Data;

@Data
public class UtilisateurResponseDTO {
    private UUID id;
    private String prenom;
    private String nom;
}
