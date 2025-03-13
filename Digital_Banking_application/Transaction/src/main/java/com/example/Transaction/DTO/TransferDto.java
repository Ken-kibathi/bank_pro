package com.example.Transaction.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@RequiredArgsConstructor
public class TransferDto {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String description;


}
