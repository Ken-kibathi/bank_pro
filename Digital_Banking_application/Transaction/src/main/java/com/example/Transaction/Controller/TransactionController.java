package com.example.Transaction.Controller;

import com.example.Transaction.DTO.TransactionDto;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Deposit funds into an account.
     */


    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam String accountNumber,
                                               @RequestParam BigDecimal amount,
                                               @RequestParam(required = false) String description) {
        Transaction transaction = transactionService.deposit(accountNumber, amount, description);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }


    /**
     * Withdraw funds from an account.
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam String accountNumber,
                                                @RequestParam BigDecimal amount,
                                                @RequestParam(required = false) String description) {
        Transaction transaction = transactionService.withdraw(accountNumber, amount, description);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Transfer funds between two accounts.
     */
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam String fromAccount,
                                                @RequestParam String toAccount,
                                                @RequestParam BigDecimal amount,
                                                @RequestParam(required = false) String description) {
        Transaction transaction = transactionService.transfer(fromAccount, toAccount, amount, description);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Get mini statement (Last 5 transactions for an account).
     */
    /*@GetMapping("/mini-statement/{accountNumber}")
    public ResponseEntity<List<Transaction>> getMiniStatement(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getMiniStatement(accountNumber);
        return ResponseEntity.ok(transactions);
    }*/
}
