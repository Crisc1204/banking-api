package com.banco.banking_api.domain.port.in;

import com.banco.banking_api.domain.model.Transaction;

import java.util.List;

public interface TransactionUseCase {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getTransactionsByAccount(Long accountId);
    List<Transaction> getAllTransactions();
    void deleteTransaction(Long id);
}
