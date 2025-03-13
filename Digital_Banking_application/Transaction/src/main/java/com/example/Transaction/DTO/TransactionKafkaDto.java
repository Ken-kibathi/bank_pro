package com.example.Transaction.DTO;

import com.example.Transaction.Entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionKafkaDto {
    private String accountNumber;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

}
