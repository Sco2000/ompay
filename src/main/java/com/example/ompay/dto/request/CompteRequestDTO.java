package com.example.ompay.dto.request;


public class CompteRequestDTO {
    private String telephone;
    private String codeConnexion;
    private UtilisateurRequestDTO utilisateur;

    // Getters et Setters
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getCodeConnexion() { return codeConnexion; }
    public void setCodeConnexion(String codeConnexion) { this.codeConnexion = codeConnexion; }
    public UtilisateurRequestDTO getUtilisateur() { return utilisateur; }
    public void setUtilisateur(UtilisateurRequestDTO utilisateur) { this.utilisateur = utilisateur; }
}
