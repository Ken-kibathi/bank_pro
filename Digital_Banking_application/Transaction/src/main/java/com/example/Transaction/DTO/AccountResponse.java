package com.example.Transaction.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AccountResponse {
    // Getters and setters
    private Long accountId;
    private String accountNumber;
    private double balance;

    // Default constructor (important for Jackson)
    public AccountResponse() {}

}
