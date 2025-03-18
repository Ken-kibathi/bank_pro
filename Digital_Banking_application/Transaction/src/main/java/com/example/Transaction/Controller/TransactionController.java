package com.example.Transaction.Controller;

import com.example.Transaction.DTO.DepositRequest;
import com.example.Transaction.DTO.TransferDto;
import com.example.Transaction.DTO.WithdrawRequest;
import com.example.Transaction.Entity.Transaction;
import com.example.Transaction.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositRequest request) {
        Transaction transaction = transactionService.deposit(request.getAccountNumber(), request.getAmount(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest request) {
        Transaction transaction = transactionService.withdraw(request.getAccountNumber(), request.getAmount(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferDto request) {
        System.out.println("Update");
        Transaction transaction = transactionService.transfer(request.getFromAccount(),request.getToAccount(),
                request.getAmount(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }


}
