package com.banco.banking_api.application.service;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.in.TransactionUseCase;
import com.banco.banking_api.domain.port.out.TransactionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final TransactionRepositoryPort transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getDate() == null) {
            transaction = transaction.toBuilder()
                    .date(LocalDateTime.now())
                    .build();
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
