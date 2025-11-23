package com.example.ompay.repository;

import com.example.ompay.entity.Transaction;
import com.example.ompay.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID>
{
    List<Transaction> findByCompteSource(Compte comte);
    List<Transaction> findByCompteDestination(Compte comte);
    List<Transaction> findByCompteSourceOrCompteDestination(Compte source, Compte destination);
}
