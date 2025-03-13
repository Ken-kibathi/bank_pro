package com.example.Transaction.Service;

import com.example.Transaction.DTO.DepositDto;
import com.example.Transaction.DTO.TransactionKafkaDto;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Entity.TransactionType;
import com.example.Transaction.Repository.AccountRepository;
import com.example.Transaction.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    /*private TransactionRepository transactionRepository;
    private AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public DepositDto deposit(String accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccountNumber(accountNumber);
        // Update account balance
        accountService.updateBalance(accountNumber, amount, true);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return new DepositDto(savedTransaction.getAccountNumber(), savedTransaction.getAmount(), savedTransaction.getTransactionDate());
    }

    *//**
     * Withdraw funds from an account.
     *//*
    public Transaction withdraw(String accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }

        // Check if balance is sufficient
        if (!accountService.hasSufficientBalance(accountNumber, amount)) {
            throw new IllegalStateException("Insufficient funds.");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccountNumber(accountNumber);

        // Deduct amount from account balance
        accountService.updateBalance(accountNumber, amount, false);

        return transactionRepository.save(transaction);
    }

    *//**
     * Transfer funds between two accounts (Atomic Transaction)
     *//*
    @Transactional
    public Transaction transfer(String fromAccount, String toAccount, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        // Check if sender has enough funds
        if (!accountService.hasSufficientBalance(fromAccount, amount)) {
            throw new IllegalStateException("Insufficient funds for transfer.");
        }

        // Withdraw from sender
        withdraw(fromAccount, amount, "Transfer to " + toAccount);

        // Deposit into receiver
        deposit(toAccount, amount, "Transfer from " + fromAccount);

        // Create and save transaction record
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccountNumber(fromAccount);
        transaction.setTargetAccountNumber(toAccount);

        return transactionRepository.save(transaction);
    }

    *//**
     * Generate mini statement (Last 5 transactions for an account).
     *//*
    public List<Transaction> getMiniStatement(String accountNumber) {
        return transactionRepository.findTop5ByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }*/

    /*private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Transactional
    public Transaction deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        return saveTransaction(accountNumber, TransactionType.DEPOSIT, amount, "Deposit successful", null);
    }

    @Transactional
    public Transaction withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        return saveTransaction(accountNumber, TransactionType.WITHDRAWAL, amount, "Withdrawal successful", null);
    }

    @Transactional
    public Transaction transfer(String senderAccountNumber, String receiverAccountNumber, BigDecimal amount) {
        Account senderAccount = accountRepository.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds for transfer");
        }

        // Deduct from sender
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        accountRepository.save(senderAccount);

        // Add to receiver
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
        accountRepository.save(receiverAccount);

        return saveTransaction(senderAccountNumber, TransactionType.TRANSFER, amount,
                "Transfer to " + receiverAccountNumber + " successful", receiverAccountNumber);
    }

    private Transaction saveTransaction(String accountNumber, TransactionType type, BigDecimal amount,
                                        String description, String targetAccountNumber) {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType(type);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTargetAccountNumber(targetAccountNumber);
        return transactionRepository.save(transaction);
    }*/

    /*private TransactionRepository transactionRepository;

    @Transactional
    public Transaction deposit(String accountNumber, BigDecimal amount, String description) {
        return saveTransaction(accountNumber, TransactionType.DEPOSIT, amount, description, null);
    }

    @Transactional
    public Transaction withdraw(String accountNumber, BigDecimal amount, String description) {
        return saveTransaction(accountNumber, TransactionType.WITHDRAWAL, amount, description, null);
    }

    @Transactional
    public Transaction transfer(String senderAccountNumber, String receiverAccountNumber, BigDecimal amount, String description) {
        return saveTransaction(senderAccountNumber, TransactionType.TRANSFER, amount, description, receiverAccountNumber);
    }

    private Transaction saveTransaction(String accountNumber, TransactionType type, BigDecimal amount,
                                        String description, String targetAccountNumber) {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType(type);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTargetAccountNumber(targetAccountNumber);
        return transactionRepository.save(transaction);
    }*/

    //private final List<Transaction> transactions = new ArrayList<>(); // In-memory storage for transactions

    private TransactionRepository transactionRepository;
    private TransactionEventProducer transactionEventProducer;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionEventProducer transactionEventProducer) {
        this.transactionRepository = transactionRepository;
        this.transactionEventProducer = transactionEventProducer;
    }

    // Deposit Method
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

        //transactions.add(transaction);
        Transaction savedtransaction = transactionRepository.save(transaction);

        /*TransactionKafkaDto event = new TransactionKafkaDto(
                accountNumber, amount,
                transaction.getTransactionDate());
        transactionEventProducer.sendTransactionEvent(event);*/

        return savedtransaction;
    }


    // Withdrawal Method
    public Transaction withdraw(String accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Withdrawal amount must be greater than zero");
        }

        // Check if there are enough funds (for simplicity, assume balance is sum of deposits - withdrawals)
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

        //transactions.add(transaction);
        return transactionRepository.save(transaction);
    }

    // Transfer Method
    public Transaction transfer(String senderAccount, String recipientAccount, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer amount must be greater than zero");
        }

        // Check if sender has enough balance
        BigDecimal senderBalance = getBalance(senderAccount);
        if (senderBalance.compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setAccountNumber(senderAccount);
        transaction.setTargetAccountNumber(recipientAccount);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());

        //transactions.add(transaction);
        return transactionRepository.save(transaction);
    }

    // Method to calculate balance for an account
    /*private BigDecimal getBalance(String accountNumber) {
        return transactions.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .map(t -> t.getTransactionType() == TransactionType.WITHDRAWAL || t.getTransactionType() == TransactionType.TRANSFER ? t.getAmount().negate() : t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }*/

    private BigDecimal getBalance(String accountNumber) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
        return transactions.stream()
                .map(t -> t.getTransactionType() == TransactionType.WITHDRAWAL || t.getTransactionType() == TransactionType.TRANSFER ? t.getAmount().negate() : t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

