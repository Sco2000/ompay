package com.example.ompay.dto.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public class CompteRequestDTO {

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "Le code de connexion est obligatoire")
    @Size(min = 4, max = 4, message = "Le code de connexion doit comporter 4 chiffres")
    private String codeConnexion;

    @Valid  // indique à Spring de valider aussi l’objet utilisateur
    @NotNull(message = "Les informations de l'utilisateur sont obligatoires")
    private UtilisateurRequestDTO utilisateur;

    // Getters et Setters
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getCodeConnexion() { return codeConnexion; }
    public void setCodeConnexion(String codeConnexion) { this.codeConnexion = codeConnexion; }
    public UtilisateurRequestDTO getUtilisateur() { return utilisateur; }
    public void setUtilisateur(UtilisateurRequestDTO utilisateur) { this.utilisateur = utilisateur; }
}
