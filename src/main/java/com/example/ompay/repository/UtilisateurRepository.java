package com.example.ompay.repository;

import com.example.ompay.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID>
{
    // JpaRepository fournit déjà toutes les méthodes CRUD
    // Exemple : save, findById, findAll, deleteById
    Optional<Utilisateur> findByCin(String cin);
}