package com.example.ompay.dto.response;


import java.util.UUID;


public class CompteResponseDTO {
    private UUID id;
    private UUID utilisateurId;
    private String telephone;
    private Double solde;
    private String prenom;
    private String nom;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public Double getSolde() {return solde;}
    public void setSolde(Double solde) {this.solde = solde;}

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public UUID getutilisateurId() { return utilisateurId; }
    public void setutilisateurId(UUID utilisateurId) { this.utilisateurId = utilisateurId; }
}
