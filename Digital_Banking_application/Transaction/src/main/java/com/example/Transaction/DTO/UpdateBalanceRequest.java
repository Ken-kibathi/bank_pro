package com.example.Transaction.DTO;

import com.example.Transaction.Entity.AccountStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Data
public class UpdateBalanceRequest {
    private Long accountId;
    private String accountNumber;
    private BigDecimal amount;
    private AccountStatus status;
    private double balance;

    public UpdateBalanceRequest() {}

    public UpdateBalanceRequest(Long accountId, String accountNumber, BigDecimal amount, AccountStatus status, double balance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
        this.balance = balance;
    }

    public UpdateBalanceRequest(String accountNumber, BigDecimal amount) {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
