package com.example.Transaction.DTO;

import com.example.Transaction.Entity.TransactionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@RequiredArgsConstructor
public class TransactionDto {

    private String accountNumber;
    private double amount;
    private String description;


}
