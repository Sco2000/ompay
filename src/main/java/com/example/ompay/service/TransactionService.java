package com.example.ompay.service;

import com.example.ompay.entity.Transaction;
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

    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions()
    {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionsById(UUID id)
    {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction)
    {
        transaction.setDateCreation(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

}
