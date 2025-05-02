package com.banco.banking_api.domain.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private LocalDateTime date;
}