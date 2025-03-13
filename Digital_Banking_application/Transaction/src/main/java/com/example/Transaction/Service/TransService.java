/*
package com.example.Transaction.Service;

import com.example.Transaction.DTO.AccountResponse;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Entity.TransactionType;
import com.example.Transaction.Repository.TransactionRepository;
import com.example.Transaction.Service.AccountClient;
import com.example.Transaction.DTO.UpdateBalanceRequest;
import com.example.Transaction.exceptions.AccountNotFoundException;
import com.example.Transaction.exceptions.InsufficientBalanceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal getBalance(Long accountId) {
        AccountResponse account = accountClient.getAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return account.getBalance(); // Returns BigDecimal
    }
    @Transactional
    public Transaction deposit(Long accountId, double amount) {
        AccountResponse account = accountClient.getAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }


        // Update balance
        accountClient.updateBalance(new UpdateBalanceRequest(accountId, amount));

        // Save transaction
        Transaction transaction = new Transaction(accountId, amount, TransactionType.DEPOSIT);
        return transactionRepository.save(transaction);
    }
    @Transactional
    public Transaction withdraw(Long accountId, double amount) {
        AccountResponse account = accountClient.getAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (account.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new InsufficientBalanceException("Not enough funds");
        }

        // Deduct balance
        accountClient.updateBalance(new UpdateBalanceRequest(accountId, amount));

        // Save transaction
        Transaction transaction = new Transaction(accountId, amount, TransactionType.WITHDRAWAL);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction transfer(Long senderId, Long receiverId, double amount) {
        AccountResponse sender = accountClient.getAccountById(senderId);
        AccountResponse receiver = accountClient.getAccountById(receiverId);

        if (sender == null || receiver == null) {
            throw new AccountNotFoundException("Sender or Receiver account not found");
        }

        if (sender.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new InsufficientBalanceException("Not enough funds");
        }
        // Deduct from sender
        accountClient.updateBalance(new UpdateBalanceRequest(senderId,-amount));

        // Add to receiver
        accountClient.updateBalance(new UpdateBalanceRequest(receiverId,amount));

        // Save transaction
        Transaction transaction = new Transaction(senderId, receiverId, amount, TransactionType.TRANSFER);
        return transactionRepository.save(transaction);
    }
}*/
