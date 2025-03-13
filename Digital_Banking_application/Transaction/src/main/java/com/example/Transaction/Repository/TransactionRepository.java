package com.example.Transaction.Repository;

import com.example.Transaction.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTop5ByAccountNumberOrderByTransactionDateDesc(String accountNumber);

    List<Transaction> findByAccountNumber(String accountNumber);
}

