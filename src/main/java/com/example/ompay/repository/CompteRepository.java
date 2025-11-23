package com.example.ompay.repository;

import com.example.ompay.entity.Compte;
import com.example.ompay.entity.enums.TypeClient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, UUID>
{
    // Ici, on peut ajouter des méthodes personnalisées plus tard
    boolean existsByTelephone(String telephone);
    Optional<Compte> findByTelephone(String telephone);
    Optional<Compte> findByCodeMarchand(String codeMarchand);
    Optional<Compte> findByTelephoneOrCodeMarchand(String telephone, String codeMarchand);
    List<Compte> findByTypeClient(TypeClient typeClient);
}
