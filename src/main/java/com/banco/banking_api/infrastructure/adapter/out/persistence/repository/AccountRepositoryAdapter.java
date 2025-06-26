package com.banco.banking_api.infrastructure.adapter.out.persistence.repository;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import com.banco.banking_api.infrastructure.adapter.out.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository jpa;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        return accountMapper.toDomain(jpa.save(accountMapper.toEntity(account)));
    }

    @Override
    public Optional<Account> findById(Long id) {
        return jpa.findById(id).map(accountMapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return jpa.findAll().stream().map(accountMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }
}
