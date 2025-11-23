package com.example.ompay.entity;

import com.example.ompay.entity.enums.TypeClient;
import com.example.ompay.entity.enums.StatutCompte;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "utilisateur")
@Entity
@Table(name = "comptes")
public class Compte {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String telephone;

    @Column(nullable = false)
    private String codeConnexion; // hashé

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeClient typeClient;

    @Enumerated(EnumType.STRING)
    private StatutCompte statut;

    private String motifSuspension;

    private String codeMarchand;

    private String qrCode;

    private LocalDateTime dateCreation = LocalDateTime.now();

    @Transient 
    private Double solde;

    @ManyToOne(cascade = CascadeType.PERSIST) 
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Compte() {}
    
    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", codeConnexion='" + codeConnexion + '\'' +
                ", typeClient=" + typeClient +
                ", statut=" + statut +
                ", motifSuspension='" + motifSuspension + '\'' +
                ", codeMarchand='" + codeMarchand + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", dateCreation=" + dateCreation +
                ", solde=" + solde +
                '}';
    }
}
