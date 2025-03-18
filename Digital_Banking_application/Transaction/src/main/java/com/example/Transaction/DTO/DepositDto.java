package com.example.Transaction.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Data
@RequiredArgsConstructor
public class DepositDto {
    private String accountNumber;
    private double amount;
    private LocalDateTime transactionDate;


}
