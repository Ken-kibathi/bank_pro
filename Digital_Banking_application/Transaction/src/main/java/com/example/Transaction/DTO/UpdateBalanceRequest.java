package com.example.Transaction.DTO;

import com.example.Transaction.Entity.AccountStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@RequiredArgsConstructor
public class UpdateBalanceRequest {
    private Long accountId;
    private String accountNumber;
    private BigDecimal amount;
    private AccountStatus status;
    private BigDecimal balance;


}
