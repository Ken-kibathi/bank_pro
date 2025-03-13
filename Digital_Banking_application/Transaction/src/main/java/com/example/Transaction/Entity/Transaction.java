package com.example.Transaction.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@Getter
@Entity
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
    private BigDecimal amount;

    private String description; // Optional transaction notes

    @Column(nullable = false, updatable=false)
    private LocalDateTime transactionDate = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        this.transactionDate = LocalDateTime.now();
    }

    // Only for TRANSFER transactions
}
