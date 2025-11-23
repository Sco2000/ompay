package com.example.ompay.controller;

import com.example.ompay.dto.request.PaiementRequestDTO;
import com.example.ompay.dto.request.TransfertRequestDTO;
import com.example.ompay.dto.response.TranfertResponseDTO;
import com.example.ompay.dto.response.ApiResponse;
import com.example.ompay.entity.Compte;
import com.example.ompay.entity.Transaction;
import com.example.ompay.mapper.TransfertMapper;
import com.example.ompay.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController
{
    private final TransactionService transactionService;
    private final TransfertMapper transfertMapper;

    public TransactionController(TransactionService transactionService, TransfertMapper transfertMapper)
    {
        this.transactionService = transactionService;
        this.transfertMapper = transfertMapper;
    }

    @GetMapping
    public ApiResponse<?> getAllTransactions(@AuthenticationPrincipal Compte compteConnecte)
    {
        try {
            List<TranfertResponseDTO> responses = transactionService.getTransactionsByCompte(compteConnecte);
            return ApiResponse.success("Transactions récupérées avec succès", responses);
        } catch (RuntimeException e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PostMapping("/transfert")
    public ApiResponse<?> createTransfertTransaction( @RequestBody TransfertRequestDTO dtoTransaction, @AuthenticationPrincipal Compte compteConnecte)
    {
        try {
            Transaction transaction = transactionService.createTransfertTransaction(dtoTransaction, compteConnecte);
            TranfertResponseDTO response = transfertMapper.toResponseDTO(transaction);
            return ApiResponse.success("Transfert effectué avec succès", response);
        } catch (RuntimeException e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PostMapping("/paiement")
    public ApiResponse<?> createPaiementTransaction(@RequestBody PaiementRequestDTO dtoPaiement, @AuthenticationPrincipal Compte compteConnecte)
    {
        try {
            Transaction transaction = transactionService.createPaiementTransaction(dtoPaiement, compteConnecte);
            TranfertResponseDTO response = transfertMapper.toResponseDTO(transaction);
            return ApiResponse.success("Paiement effectué avec succès", response);
        } catch (RuntimeException e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}
