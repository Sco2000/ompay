package com.example.ompay.entity;

import com.example.ompay.entity.enums.TypeTransaction;
import com.example.ompay.entity.enums.StatutTransaction;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private StatutTransaction statut;

    @ManyToOne
    @JoinColumn(name = "compte_source_id")
    private Compte compteSource;

    @ManyToOne
    @JoinColumn(name = "compte_destination_id")
    private Compte compteDestination;

    public Transaction() {}

    // public UUID getId() { return id; }
    // public void setId(UUID id) { this.id = id; }

    // public Double getMontant() { return montant; }
    // public void setMontant(Double montant) { this.montant = montant; }

    // public TypeTransaction getType() { return type; }
    // public void setType(TypeTransaction type) { this.type = type; }

    // public LocalDateTime getDateCreation() { return dateCreation; }
    // public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    // public StatutTransaction getStatut() { return statut; }
    // public void setStatut(StatutTransaction statut) { this.statut = statut; }

    // public Compte getCompteSource() { return compteSource; }
    // public void setCompteSource(Compte compteSource) { this.compteSource = compteSource; }

    // public Compte getCompteDestination() { return compteDestination; }
    // public void setCompteDestination(Compte compteDestination) { this.compteDestination = compteDestination; }
}
