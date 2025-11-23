package com.example.ompay.service;

import com.example.ompay.dto.request.PaiementRequestDTO;
import com.example.ompay.dto.request.TransfertRequestDTO;
import com.example.ompay.dto.response.TranfertResponseDTO;
import com.example.ompay.entity.Compte;
import com.example.ompay.entity.Transaction;
import com.example.ompay.entity.enums.StatutTransaction;
import com.example.ompay.entity.enums.TypeClient;
import com.example.ompay.entity.enums.TypeTransaction;
import com.example.ompay.mapper.PaiementMapper;
import com.example.ompay.mapper.TransfertMapper;
import com.example.ompay.repository.CompteRepository;
import com.example.ompay.repository.TransactionRepository;
// import com.example.ompay.entity.enums.StatutTransaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService
{
    private final TransactionRepository transactionRepository;
    private final CompteService compteService;
    private final CompteRepository compteRepository;
    private final TransfertMapper transfertMapper;
    private final PaiementMapper paiementMapper;

    public TransactionService(TransactionRepository transactionRepository, CompteService compteService, CompteRepository compteRepository, TransfertMapper transfertMapper, PaiementMapper paiementMapper)
    {
        this.transactionRepository = transactionRepository;
        this.compteService = compteService;
        this.compteRepository = compteRepository;
        this.transfertMapper = transfertMapper;
        this.paiementMapper = paiementMapper;
    }

    public List<Transaction> getAllTransactions()
    {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionsById(UUID id)
    {
        return transactionRepository.findById(id);
    }

    public Transaction createTransfertTransaction(TransfertRequestDTO transactionDto, Compte compteConnecte)
    {
        Transaction transaction = transfertMapper.toEntity(transactionDto);

        transaction.setCompteSource(compteConnecte);

        Compte compteDestinataire = compteService.getCompteByTelephone(transactionDto.getTelephoneDestinataire())
                .orElseThrow(() -> new RuntimeException("Le compte destinataire n’existe pas"));

        if (compteDestinataire.getId().equals(compteConnecte.getId())) {
            throw new RuntimeException("On ne peut pas s'envoyer de l'argent à soi-même");
        }

        transaction.setCompteDestination(compteDestinataire);
        
        Double compteSolde = compteService.calculerSolde(compteConnecte);

        if (compteSolde < transactionDto.getMontant()) {
            throw new RuntimeException("Votre solde est insuffisant!");
        }

        Double montant = transactionDto.getMontant();
        Double frais = 0.00;

        if (compteConnecte.getTypeClient() != TypeClient.DISTRIBUTEUR) {
            frais = 0.00;
        }

        Double montantTotal = montant + frais;
        transaction.setMontant(montant);
        transaction.setFrais(frais);
        transaction.setMontantTotal(montantTotal);
        transaction.setStatut(StatutTransaction.REUSSI);
        return transactionRepository.save(transaction);
    }

    public Transaction createPaiementTransaction(PaiementRequestDTO paiementDto, Compte compteConnecte)
    {
        // Vérifier que seul un client ou marchand peut effectuer un paiement
        if (compteConnecte.getTypeClient() != TypeClient.CLIENT && compteConnecte.getTypeClient() != TypeClient.MARCHAND) {
            throw new RuntimeException("Seul un client ou un marchand peut effectuer un paiement");
        }

        Transaction transaction = paiementMapper.toEntity(paiementDto);

        transaction.setCompteSource(compteConnecte);

        Compte compteDestinataire = compteRepository.findByTelephoneOrCodeMarchand(paiementDto.getIdentifiantMarchand(), paiementDto.getIdentifiantMarchand())
                .orElseThrow(() -> new RuntimeException("Le compte destinataire n'existe pas"));

        if (compteDestinataire.getId().equals(compteConnecte.getId())) {
            throw new RuntimeException("On ne peut pas se faire un paiement à soi-même");
        }

        if (compteDestinataire.getTypeClient() != TypeClient.MARCHAND) {
            throw new RuntimeException("On ne peut faire un paiement qu'à un compte marchand");
        }

        transaction.setCompteDestination(compteDestinataire);

        Double compteSolde = compteService.calculerSolde(compteConnecte);

        if (compteSolde < transaction.getMontant()) {
            throw new RuntimeException("Votre solde est insuffisant!");
        }

        Double montant = paiementDto.getMontant();
        Double frais = 0.00;

        Double montantTotal = montant + frais;
        transaction.setMontant(montant);
        transaction.setFrais(frais);
        transaction.setMontantTotal(montantTotal);
        transaction.setStatut(StatutTransaction.REUSSI);
        return transactionRepository.save(transaction);
    }

    public List<TranfertResponseDTO> getTransactionsByCompte(Compte compte) {
        List<Transaction> transactions = transactionRepository.findByCompteSourceOrCompteDestination(compte, compte);

        return transactions.stream()
            .map(transaction -> {
                TranfertResponseDTO dto = new TranfertResponseDTO();
                dto.setId(transaction.getId());
                dto.setMontant(transaction.getMontant());
                dto.setFrais(transaction.getFrais());
                dto.setDateCreation(transaction.getDateCreation());

                if (transaction.getType() == TypeTransaction.DEPOT) {
                    dto.setType("retrait d'argent");
                    if (transaction.getCompteSource().getId().equals(compte.getId())) {
                        dto.setMontantTotal("+" + transaction.getMontantTotal());
                    } else {
                        dto.setMontantTotal("-" + transaction.getMontantTotal());
                    }
                } else {
                    dto.setType("paiement");
                    if (transaction.getCompteSource().getId().equals(compte.getId())) {
                        dto.setMontantTotal("+" + transaction.getMontantTotal());
                    } else {
                        dto.setMontantTotal("-" + transaction.getMontantTotal());
                    }
                }

                // Définir le destinataire selon le type de transaction
                if (transaction.getType() == TypeTransaction.DEPOT) {
                    dto.setDestinataire(transaction.getCompteDestination().getTelephone());
                } else {
                    dto.setDestinataire(transaction.getCompteDestination().getTelephone());
                }

                return dto;
            })
            .toList();
    }

}
