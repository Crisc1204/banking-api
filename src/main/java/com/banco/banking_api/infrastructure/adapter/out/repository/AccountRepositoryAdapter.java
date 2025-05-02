package com.banco.banking_api.infrastructure.adapter.out.repository;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import com.banco.banking_api.infrastructure.adapter.out.mapper.AccountMapper;
import com.banco.banking_api.infrastructure.adapter.out.persistence.AccountJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository jpa;

    public AccountRepositoryAdapter(AccountJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Account save(Account account) {
        return AccountMapper.toDomain(jpa.save(AccountMapper.toEntity(account)));
    }

    @Override
    public Optional<Account> findById(Long id) {
        return jpa.findById(id).map(AccountMapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return jpa.findAll().stream().map(AccountMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }
}
