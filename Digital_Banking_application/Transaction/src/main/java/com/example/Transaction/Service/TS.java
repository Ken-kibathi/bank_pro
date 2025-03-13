/*
package com.example.Transaction.Service;

import com.example.Transaction.DTO.TransactionDto;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Entity.TransactionType;
import com.example.Transaction.Repository.AccountRepository;
import com.example.Transaction.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TS {
    private TransactionRepository transactionRepository;
    private AccountService accountService;// Assume AccountService exists
    private AccountRepository accountRepository;

    public TS(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Transactional
    public Transaction processTransaction(TransactionDto request) {
        BigDecimal amount = request.getAmount();
        String accountNumber = request.getAccountNumber();
        String targetAccount = request.getTargetAccountNumber();
        TransactionType type = request.getTransactionType();

        if (type == TransactionType.DEPOSIT) {
            accountService.creditAccount(accountNumber, amount);
        } else if (type == TransactionType.WITHDRAWAL) {
            accountService.debitAccount(accountNumber, amount);
        } else if (type == TransactionType.TRANSFER) {
            if (targetAccount == null || targetAccount.isEmpty()) {
                throw new IllegalArgumentException("Target account is required for transfers.");
            }
            accountService.debitAccount(accountNumber, amount);
            accountService.creditAccount(targetAccount, amount);
        } else {
            throw new IllegalArgumentException("Invalid transaction type.");
        }

        // Save the transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTargetAccountNumber(targetAccount);
        transaction.setAmount(amount);
        transaction.setTransactionType(type);
        transaction.setDescription(request.getDescription());
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
*/

