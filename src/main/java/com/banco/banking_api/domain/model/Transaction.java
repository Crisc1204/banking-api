package com.banco.banking_api.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String type; // "DEPOSIT" o "WITHDRAWAL"
    private LocalDateTime date;
}