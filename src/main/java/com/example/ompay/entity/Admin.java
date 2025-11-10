package com.example.ompay.entity;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Entity representing an administrator in the system.
 * <p>
 * Each Admin has a unique UUID identifier, a unique login, and a password.
 * </p>
 */
@Entity
@Table(name="admins")
public class Admin {

    /**
     * Identifiant unique de l'administrateur.
     */
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    /**
     * Nom d'utilisateur de l'administrateur (doit être unique).
     */
    @Column(nullable = false, unique = true)
    private String login;

    /**
     * Mot de passe de l'administrateur.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Constructeur par défaut.
     */
    public Admin() {}

    /**
     * Constructeur avec paramètres.
     * 
     * @param login Nom d'utilisateur de l'administrateur.
     * @param password Mot de passe de l'administrateur.
     */
    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Retourne l'identifiant unique de l'administrateur.
     * 
     * @return UUID de l'administrateur.
     */
    public UUID getId() { return id; }

    /**
     * Retourne le nom d'utilisateur de l'administrateur.
     * 
     * @return login de l'administrateur.
     */
    public String getLogin() { return login; }

    /**
     * Définit le nom d'utilisateur de l'administrateur.
     * 
     * @param login Nouveau nom d'utilisateur.
     */
    public void setLogin(String login) { this.login = login; }

    /**
     * Retourne le mot de passe de l'administrateur.
     * 
     * @return Mot de passe de l'administrateur.
     */
    public String getPassword() { return password; }

    /**
     * Définit le mot de passe de l'administrateur.
     * 
     * @param password Nouveau mot de passe.
     */
    public void setPassword(String password) { this.password = password; }
}
