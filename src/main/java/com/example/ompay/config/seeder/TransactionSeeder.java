package com.example.ompay.config.seeder;

import com.example.ompay.entity.Compte;
import com.example.ompay.entity.Transaction;
import com.example.ompay.entity.enums.TypeClient;
import com.example.ompay.entity.enums.TypeTransaction;
import com.example.ompay.repository.TransactionRepository;
import com.example.ompay.service.CompteService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
// import java.util.UUID;

@Component
public class TransactionSeeder implements CommandLineRunner {

    private final TransactionRepository transactionRepository;
    private final CompteService compteService;

    public TransactionSeeder(TransactionRepository transactionRepository, CompteService compteService) {
        this.transactionRepository = transactionRepository;
        this.compteService = compteService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifie si la table est vide
        if(transactionRepository.count() > 0) return;

        // Récupération de quelques comptes pour le seeding
        List<Compte> comptes = compteService.getComptesByTypeClient(TypeClient.DISTRIBUTEUR);
        if(comptes.size() < 1) {
            System.out.println("Pas assez de comptes pour seed les transactions !");
            return;
        }

        Compte compte1 = comptes.get(0);

        // Création d'une transaction de type DEPOT (transfert)
        Transaction transfert = new Transaction();
        transfert.setMontant(5000.0);
        transfert.setType(TypeTransaction.DEPOT); // toujours DEPOT en base
        transfert.setCompteDestination(compte1);
        transfert.setDateCreation(LocalDateTime.now().minusDays(1));
        transfert.setStatut(com.example.ompay.entity.enums.StatutTransaction.REUSSI);
        transactionRepository.save(transfert);

        // Création d'une transaction de type PAIEMENT
        Transaction paiement = new Transaction();
        paiement.setMontant(2000.0);
        paiement.setType(TypeTransaction.DEPOT);
        paiement.setCompteDestination(compte1);
        paiement.setDateCreation(LocalDateTime.now());
        paiement.setStatut(com.example.ompay.entity.enums.StatutTransaction.REUSSI);
        transactionRepository.save(paiement);

        System.out.println("Seed des transactions effectué !");
    }
}
