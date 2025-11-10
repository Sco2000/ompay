package com.example.ompay.service;

import com.example.ompay.entity.Compte;
import com.example.ompay.entity.Transaction;
import com.example.ompay.entity.Utilisateur;
import com.example.ompay.entity.enums.StatutCompte;
import com.example.ompay.entity.enums.StatutTransaction;
import com.example.ompay.repository.CompteRepository;
import com.example.ompay.repository.TransactionRepository;
import com.example.ompay.repository.UtilisateurRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
public class CompteService {
    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public CompteService(CompteRepository compteRepository, TransactionRepository transactionRepository, UtilisateurRepository utilisateurRepository)
    {
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Compte> getAllComptes()
    {
        List<Compte> comptes = compteRepository.findAll();
        for (Compte compte : comptes) {
            compte.setSolde(this.calculerSolde(compte));
        }

        return comptes;
    }

    public Optional<Compte> getCompteById(UUID id)
    {
        Optional<Compte> compteOpt = compteRepository.findById(id);
        compteOpt.ifPresent(compte -> compte.setSolde(this.calculerSolde(compte)));

        return compteOpt;
    }

    public Optional<Compte> getCompteByTelephone(String telephone)
    {
        Optional<Compte> compteOpt = compteRepository.findByTelephone(telephone);
        compteOpt.ifPresent(compte -> compte.setSolde(this.calculerSolde(compte)));

        return compteOpt;
    }

    @Transactional
    public Compte createCompte(Compte compte) {

        if (compteRepository.existsByTelephone(compte.getTelephone())) {
            throw new RuntimeException("Ce numéro de téléphone est déjà associé à un compte !");
        }

        String cin = compte.getUtilisateur().getCin();
        if (cin == null || cin.isBlank()) {
            throw new RuntimeException("Le CIN de l'utilisateur ne peut pas être nul ou vide !");
        }

        Utilisateur utilisateur = utilisateurRepository.findByCin(cin)
                .orElseGet(() -> utilisateurRepository.save(compte.getUtilisateur()));

        compte.setUtilisateur(utilisateur);

        compte.setStatut(StatutCompte.ACTIF);
        Compte save = compteRepository.save(compte);
        System.out.println("Utilisateur du compte : " + compte.getUtilisateur());

        save.setSolde(0.0);
        return save;
    }


    public Double calculerSolde(Compte compte)
    {
        List<Transaction> transactionsSources = transactionRepository.findByCompteSource(compte);
        List<Transaction> transactionsDestinations = transactionRepository.findByCompteDestination(compte);

        Double solde = 0.0;

        for (Transaction transaction : transactionsDestinations) {
            if (transaction.getStatut() == StatutTransaction.REUSSI) {
                solde += transaction.getMontant();
            }
        }

        for (Transaction transaction : transactionsSources) {
            if(transaction.getStatut() == StatutTransaction.REUSSI) {
                solde -= transaction.getMontant();
            }
        }
        compte.setSolde(solde);
        return solde;
    }
}
