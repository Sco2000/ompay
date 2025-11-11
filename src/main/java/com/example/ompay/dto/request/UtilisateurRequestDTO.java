package com.example.ompay.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.*;


public class UtilisateurRequestDTO {

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @Email(message = "L'adresse email est invalide")
    private String email;

    @NotBlank(message = "Le CIN est obligatoire")
    @Size(min = 13, max = 13, message = "Le CIN doit contenir exactement 13 caractères")
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
