package com.banco.banking_api.application.service;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.in.TransactionUseCase;
import com.banco.banking_api.domain.port.out.TransactionRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionUseCase {

    private final TransactionRepositoryPort repository;

    public TransactionService(TransactionRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setDate(java.time.LocalDateTime.now());
        return repository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}
