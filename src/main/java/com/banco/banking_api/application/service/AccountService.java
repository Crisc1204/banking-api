package com.banco.banking_api.application.service;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.in.AccountUseCase;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountUseCase {

    private final AccountRepositoryPort repository;

    public AccountService(AccountRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    public Optional<Account> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account update(Account account) {
        return repository.save(account);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}