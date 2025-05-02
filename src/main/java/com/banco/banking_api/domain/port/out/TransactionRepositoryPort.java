package com.banco.banking_api.domain.port.out;

import com.banco.banking_api.domain.model.Transaction;

import java.util.List;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findAll();
    void deleteById(Long id);
}