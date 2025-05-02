package com.banco.banking_api.application.service;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.in.CreateAccountUseCase;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    //private final AccountRepositoryPort repository;
    private final AccountRepositoryPort accountRepo;
    private final TransactionRepository transactionRepo;

    public Account createAccount(Account acc) {
        acc.setBalance(acc.getBalance() != null ? acc.getBalance() : 0.0);
        return accountRepo.save(acc);
    }

    public Account getAccount(Long id) {
        return accountRepo.findById(id).orElseThrow();
    }

    public void transfer(Long fromId, Long toId, Double amount) {
        Account from = getAccount(fromId);
        Account to = getAccount(toId);

        if (from.getBalance() < amount) throw new RuntimeException("Fondos insuficientes");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        accountRepo.save(from);
        accountRepo.save(to);

        transactionRepo.save(new Transaction(null, fromId, toId, amount, LocalDateTime.now()));
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepo.findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}