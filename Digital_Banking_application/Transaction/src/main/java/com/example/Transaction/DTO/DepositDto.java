package com.example.Transaction.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositDto {
    private String accountNumber;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    public DepositDto(String accountNumber, BigDecimal amount, LocalDateTime transactionDate) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
