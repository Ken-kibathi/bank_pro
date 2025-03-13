package com.example.Transaction.Service;

import com.example.Transaction.Entity.Account;
import com.example.Transaction.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void creditAccount(String accountNumber, BigDecimal amount) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (optionalAccount.isEmpty()) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }

        Account account = optionalAccount.get();
        account.setBalance(account.getBalance().add(amount)); // Increase balance
        accountRepository.save(account);
    }

    /**
     * Withdraw (Debit) money from an account
     */
    @Transactional
    public void debitAccount(String accountNumber, BigDecimal amount) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (optionalAccount.isEmpty()) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }

        Account account = optionalAccount.get();
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds in account: " + accountNumber);
        }

        account.setBalance(account.getBalance().subtract(amount)); // Decrease balance
        accountRepository.save(account);
    }

    public boolean hasSufficientBalance(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return account.getBalance().compareTo(amount) >= 0;
    }

    /**
     * Updates the account balance (Deposit = true, Withdraw = false)
     */
    @Transactional
    public void updateBalance(String accountNumber, BigDecimal amount, boolean isDeposit) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (!isDeposit && account.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds for withdrawal");
        }

        // Update balance
        account.setBalance(isDeposit ? account.getBalance().add(amount) : account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
}
