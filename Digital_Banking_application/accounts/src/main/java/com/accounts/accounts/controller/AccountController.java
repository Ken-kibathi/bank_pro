package com.accounts.accounts.controller;

import com.accounts.accounts.dto.AccountDto;
import com.accounts.accounts.exception.ResourceNotFoundException;
import com.accounts.accounts.model.Account;
import com.accounts.accounts.repository.AccountRepository;
import com.accounts.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository repository;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam Long customerId) {
        Account account = accountService.createAccount(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String accountNumber) {
        Account account = accountService.getAccountBalance(accountNumber);
        return ResponseEntity.ok(account.getBalance());
    }


    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNo(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNo(accountNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account not found for number: " + accountNumber));
        return ResponseEntity.ok(account);
    }

    @PutMapping("/freeze/{id}")
    public ResponseEntity<String> freezeAccount(@PathVariable Long id) {
        accountService.freezeAccount(id);
        return ResponseEntity.ok("Account " + accountService.getAccountNumber(id) + "   frozen successfully.");
    }

    @PutMapping("/unfreeze/{id}")
    public ResponseEntity<String> unfreezeAccount(@PathVariable Long id) {
        accountService.unfreezeAccount(id);
        return ResponseEntity.ok("Account " + accountService.getAccountNumber(id) + "  unfrozen successfully.");
    }
/*    @PutMapping("/updateBalance/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody AccountDto accountDetails) {
        Account updateAccount = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Account does not exist" + id));
        updateAccount.setBalance(accountDetails.getBalance());
        repository.save(updateAccount);
        return ResponseEntity.ok(updateAccount);
    }*/
@PutMapping("{accountNumber}/{amount}")
public ResponseEntity<String> updateBalance(@PathVariable String accountNumber, @PathVariable double amount) throws AccountNotFoundException {
    System.out.println("Account Number = " + accountNumber + ", Amount = " + amount);

    accountService.updateBalance(accountNumber, amount);

    return ResponseEntity.ok("Balance updated successfully.");
}
}
