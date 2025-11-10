package com.example.ompay.service;

import com.example.ompay.entity.Utilisateur;
import com.example.ompay.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtilisateurService
{
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository)
    {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateur()
    {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(UUID id)
    {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur)
    {
        return utilisateurRepository.save(utilisateur);
    }
}
