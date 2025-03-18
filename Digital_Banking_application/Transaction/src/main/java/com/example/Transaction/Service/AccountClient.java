package com.example.Transaction.Service;

import com.example.Transaction.DTO.UpdateBalanceRequest;
import com.example.Transaction.Entity.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Transaction.DTO.AccountResponse;

import java.math.BigDecimal;

@FeignClient(name = "accounts")
public interface AccountClient {

    @GetMapping("/api/accounts/balance/{accountNumber}")
    AccountResponse getBalance(@PathVariable String accountNumber);

    @GetMapping("/api/accounts/{accountNumber}")
    AccountResponse getAccountByAccNo(@PathVariable String accountNumber);

    @PutMapping("/api/accounts/{accountNumber}/{amount}")
        void updateAccBalance(@PathVariable String accountNumber, @PathVariable double amount);

}