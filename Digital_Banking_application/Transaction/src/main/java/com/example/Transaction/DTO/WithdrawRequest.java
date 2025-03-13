package com.example.Transaction.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@RequiredArgsConstructor
public class WithdrawRequest {
    private String accountNumber;
    private BigDecimal amount;
    private String description;


}
