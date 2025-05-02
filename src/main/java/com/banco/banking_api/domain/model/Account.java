package com.banco.banking_api.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {
    private Long id;
    private String owner;
    private BigDecimal balance;

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public Long getId() { return id; }
    public BigDecimal getBalance() { return balance; }
}