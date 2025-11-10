package com.example.ompay.dto.request;

import java.time.LocalDate;

public class UtilisateurRequestDTO {
    private String prenom;
    private String nom;
    private String email;
    private String cin;
    private String adresse;
    private LocalDate dateDeNaissance;

    // Getters et Setters
    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }

    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCin() { return cin; }

    public void setCin(String cin) { this.cin = cin; }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public LocalDate getDateDeNaissance() { return dateDeNaissance; }

    public void setDateDeNaissance(LocalDate dateDeNaissance) { this.dateDeNaissance = dateDeNaissance; }

}
