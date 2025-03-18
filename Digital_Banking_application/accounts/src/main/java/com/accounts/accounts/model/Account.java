package com.accounts.accounts.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Lock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Data
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @Enumerated(EnumType.STRING)
    private AccountStatus status; // ACTIVE, FROZEN, CLOSED

    @Column(nullable = false)
    private double balance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


/*@Version
    private Integer version;*/


}
