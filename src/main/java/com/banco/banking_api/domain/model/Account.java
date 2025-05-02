package com.banco.banking_api.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private String id;
    private String customerId;
    private BigDecimal balance;

    public Account(String customerId) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public BigDecimal getBalance() { return balance; }
}