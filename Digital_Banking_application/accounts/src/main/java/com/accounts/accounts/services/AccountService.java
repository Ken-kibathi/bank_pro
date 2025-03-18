package com.accounts.accounts.services;

import com.accounts.accounts.model.Account;
import com.accounts.accounts.model.AccountStatus;
import com.accounts.accounts.model.Customer;
import com.accounts.accounts.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.Optional;

import static com.accounts.accounts.config.AccountUtils.generateAccountNumber;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerService customerService;

    public Account createAccount(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);

        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountNumber(generateAccountNumber());
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(0.0);
        account.setCreatedAt(new Date());
        return accountRepository.save(account);
    }



    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    public String getAccountNumber(Long id) {
        return accountRepository.findById(id)
                .map(Account::getAccountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + id));
    }

    public void freezeAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.setStatus(AccountStatus.FROZEN);
        accountRepository.save(account);
    }

    public void unfreezeAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.setStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }

    public void updateBalance(Long accountId) {
        Account account = getAccountById(accountId);
        account.setBalance(account.getBalance());
        accountRepository.save(account);
    }

    public Optional<Account> getAccountByAccountNo(String AccountNumber) {
        return Optional.ofNullable(accountRepository.findByAccountNumber(AccountNumber));
    }
    @Transactional

    public void updateBalance(String accountNumber, double amount) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
             //   .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setBalance(account.getBalance()+(amount));
        accountRepository.save(account);
    }


    public Account getAccountBalance(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}

