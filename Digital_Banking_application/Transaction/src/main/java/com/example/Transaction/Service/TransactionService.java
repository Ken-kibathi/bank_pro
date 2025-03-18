package com.example.Transaction.Service;

import com.example.Transaction.DTO.AccountResponse;
import com.example.Transaction.DTO.UpdateBalanceRequest;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Entity.TransactionType;
import com.example.Transaction.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionEventProducer transactionEventProducer;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionEventProducer transactionEventProducer) {
        this.transactionRepository = transactionRepository;
        this.transactionEventProducer = transactionEventProducer;
    }

    @Autowired
    private AccountClient accountClient;

    @Transactional
    public Transaction deposit(String accountNumber, double amount, String description) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deposit amount must be greater than zero");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        System.out.println("Account Number  " + accountNumber + " Amount is " + amount);
        accountClient.updateAccBalance(accountNumber, amount);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction withdraw(String accountNumber, double amount, String description) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Withdrawal amount must be greater than zero");
        }

        double balance = Optional.ofNullable(accountClient.getBalance(accountNumber))
                .map(AccountResponse::getBalance)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to fetch account balance"));

        if (balance < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        System.out.println("The balance is " + balance);
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        System.out.println("Account Number  " + accountNumber + " Amount is " + (-amount));
        accountClient.updateAccBalance(accountNumber, -amount);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction transfer(String senderAccount, String recipientAccount, double amount, String description) {
        System.out.println(senderAccount + recipientAccount + amount + description + "Random");

        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer amount must be greater than zero");
        }

        System.out.println("Amount" + amount + " Sender Balance " + senderAccount);
        double senderBalance = accountClient.getBalance(senderAccount).getBalance();
        System.out.println("Amount" + amount + " Sender Balance " + senderBalance);
        if (senderBalance < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        System.out.println(senderAccount + recipientAccount + amount + description);
        // Deduct from sender
        accountClient.updateAccBalance(senderAccount, -amount);
        // Add to recipient
        accountClient.updateAccBalance(recipientAccount, amount);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setAccountNumber(senderAccount);
        transaction.setTargetAccountNumber(recipientAccount);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    private double getBalance(String accountNumber) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
        return transactions.stream()
                .mapToDouble(t -> t.getTransactionType() == TransactionType.WITHDRAWAL || t.getTransactionType() == TransactionType.TRANSFER ? -t.getAmount() : t.getAmount())
                .sum();
    }
}
