package com.example.ompay.entity;

import jakarta.persistence.*; 
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

/**
 * Represents a user entity in the system.
 * This class stores basic user information including name, email and creation date.
 *
 * @Entity JPA entity representing a user table in the database
 */
@Entity
@Table(name="utilisateurs")
public class Utilisateur {
    /**
     * Unique identifier for the user
     */
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id; 

    /**
     * Last name of the user
     */
    @Column(nullable = false)
    private String nom;

    /**
     * First name of the user
     */
    @Column(nullable = false)
    private String prenom;

    /**
     * Email address of the user (optional)
     */
    @Column(nullable = true, unique = true)
    private String email;

    /**
     * Identity number of the user (optional)
     */
    @Column(nullable = false, unique = true)
    private String cin;

    /**
     * Adresse of the user (optional)
     */
    @Column(nullable = false)
    private String adresse;

    /**
     * Date of birth of the user (optional)
     */
    @Column(nullable = false)
    private LocalDate dateDeNaissance;

    /**
     * Timestamp when the user was created
     */
    @Column(nullable = false)
    private LocalDateTime dateCreation;

    /**
     * All comptes of the user (optional)
     */
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compte> comptes;

    /**
     * Default constructor.
     * Initializes dateCreation to current timestamp.
     */

    /**
     * Parameterized constructor to create a user with basic information.
     *
     * @param nom     The last name of the user
     * @param prenom  The first name of the user
     * @param email   The email address of the user
     */

    public Utilisateur() {
        this.dateCreation = LocalDateTime.now(); 
    }

    public Utilisateur(String nom, String prenom, String email, String cin, String adresse, LocalDate dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.adresse = adresse;
        this.dateDeNaissance = dateDeNaissance;
        this.dateCreation = LocalDateTime.now();
    }

    // Getters and Setters documentation
    /**
     * @return The unique identifier of the user
     */
    public UUID getId() { return id; }

    /**
     * @return The last name of the user
     */
    public String getNom() { return nom; }

    /**
     * @param nom The last name to set
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * @return The first name of the user
     */
    public String getPrenom() { return prenom; }

    /**
     * @param prenom The first name to set
     */
    public void setPrenom(String prenom) { this.prenom = prenom; }

    /**
     * @return The email address of the user
     */
    public String getEmail() { return email; }

    /**
     * @param email The email address to set
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * @return The identity number of the user
     */
    public String getCin() { return cin; }

    /**
     * @param cin The identity number to set
     */
    public void setCin(String cin) { this.cin = cin; }

     /**
     * @return The adress of the user
     */
    public String getAdresse() { return adresse; }

    /**
     * @param adresse The adress to set
     */
    public void setAdresse(String adresse) { this.adresse = adresse; }

    /**
     * @return The date of birth of the user
     */
    public LocalDate getDateDeNaissance() { return dateDeNaissance; }

    /**
     * @param dateDeNaissance The date of birth to set
     */
    public void setDateDeNaissance(LocalDate dateDeNaissance) { this.dateDeNaissance = dateDeNaissance; }

    /**
     * @return The creation timestamp of the user
     */
    public LocalDateTime getDateCreation() { return dateCreation; }

    /**
     * @param dateCreation The creation timestamp to set
     */
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

}