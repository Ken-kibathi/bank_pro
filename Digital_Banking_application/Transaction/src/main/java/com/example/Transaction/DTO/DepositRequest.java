package com.example.Transaction.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DepositRequest {
    private String accountNumber;
    private double amount;
    private String description;

    public DepositRequest() {}

    public DepositRequest(String accountNumber, double amount, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
    }

}
