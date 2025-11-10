package com.example.ompay.repository;

import com.example.ompay.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, UUID>
{
    // Ici, on peut ajouter des méthodes personnalisées plus tard
    boolean existsByTelephone(String telephone);
    Optional<Compte> findByTelephone(String telephone);
}
