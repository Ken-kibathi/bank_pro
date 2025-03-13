package com.example.Transaction.DTO;

import com.example.Transaction.Entity.TransactionType;

import java.math.BigDecimal;

public class TransactionDto {

    private String accountNumber;
    private BigDecimal amount;
    private String description;

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionDto(String accountNumber, BigDecimal amount, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
