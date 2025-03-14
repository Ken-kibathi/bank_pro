package com.example.Transaction.Service;

import com.example.Transaction.DTO.UpdateBalanceRequest;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Entity.TransactionType;
import com.example.Transaction.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    public Transaction deposit(String accountNumber, BigDecimal amount,
                               String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
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
    public Transaction withdraw(String accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Withdrawal amount must be greater than zero");
        }

        BigDecimal balance = getBalance(accountNumber);
        if (balance.compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        System.out.println("Account Number  " + accountNumber + " Amount is " + amount.negate());
        accountClient.updateAccBalance(accountNumber, amount.negate());
        return transactionRepository.save(transaction);

    }
    @Transactional
    public Transaction transfer(String senderAccount, String recipientAccount, BigDecimal amount, String description) {
        System.out.println(senderAccount + recipientAccount + amount + description + "Random");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer amount must be greater than zero");
        }
        System.out.println("Amount");

/*        BigDecimal senderBalance = getBalance(senderAccount);
        if (senderBalance.compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }*/

        System.out.println(senderAccount + recipientAccount + amount + description);
        // Deduct from sender

        accountClient.updateAccBalance(senderAccount, amount.negate());
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


    private BigDecimal getBalance(String accountNumber) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
        return transactions.stream()
                .map(t -> t.getTransactionType() == TransactionType.WITHDRAWAL || t.getTransactionType() == TransactionType.TRANSFER ? t.getAmount().negate() : t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

