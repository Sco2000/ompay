package com.example.ompay.entity;

import com.example.ompay.entity.enums.TypeClient;
import com.example.ompay.entity.enums.StatutCompte;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @Transient // <-- cet attribut ne sera pas stocké dans la BD
    private Double solde;

    @ManyToOne(cascade = CascadeType.PERSIST) 
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Compte() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getCodeConnexion() { return codeConnexion; }
    public void setCodeConnexion(String codeConnexion) { this.codeConnexion = codeConnexion; }

    public TypeClient getTypeClient() { return typeClient; }
    public void setTypeClient(TypeClient typeClient) { this.typeClient = typeClient; }

    public StatutCompte getStatut() { return statut; }
    public void setStatut(StatutCompte statut) { this.statut = statut; }

    public String getMotifSuspension() { return motifSuspension; }
    public void setMotifSuspension(String motifSuspension) { this.motifSuspension = motifSuspension; }

    public String getCodeMarchand() { return codeMarchand; }
    public void setCodeMarchand(String codeMarchand) { this.codeMarchand = codeMarchand; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Double getSolde() {return solde;}
    public void setSolde(Double solde) {this.solde = solde;}
}
