package com.accounts.accounts.dto;

import com.accounts.accounts.model.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
//    private Long id;
    private String accountNumber;
    private AccountStatus status;
    private double balance;
}
