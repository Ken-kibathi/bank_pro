package com.example.Transaction.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"

    @Column(nullable = false )
    private String accountNumber; // The account involved in the transaction

    private String targetAccountNumber;

    @Column(nullable = false)
    private double amount;

    private String description; // Optional transaction notes

    @Column(nullable = false, updatable=false)
    private LocalDateTime transactionDate = LocalDateTime.now();

    public Transaction(Long accountId, double amount, TransactionType transactionType) {
    }

    public Transaction(Long senderId, Long receiverId, double amount, TransactionType transactionType) {
    }




    @PrePersist
    protected void onCreate() {
        this.transactionDate = LocalDateTime.now();
    }

    // Only for TRANSFER transactions
}
