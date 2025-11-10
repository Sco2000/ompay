package com.example.ompay.dto.response;

import java.util.UUID;

public class UtilisateurResponseDTO {
    private UUID id;
    private String prenom;
    private String nom;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
