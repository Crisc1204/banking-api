package com.banco.banking_api.domain.port.in;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.model.Transaction;

import java.util.List;

public interface CreateAccountUseCase {
    void createAccount(String customerId);

    Account createAccount(Account account);
    Account getAccount(Long id);
    void transfer(Long fromId, Long toId, Double amount);
    List<Transaction> getTransactions(Long accountId);
}