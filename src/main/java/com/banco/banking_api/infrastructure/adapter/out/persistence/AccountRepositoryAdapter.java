package com.banco.banking_api.infrastructure.adapter.out.persistence;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import com.banco.banking_api.infrastructure.adapter.out.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository jpaRepository;
    private final AccountMapper mapper;

    @Override
    public Account save(Account account) {
        jpaRepository.save(mapper.toEntity(account));
        return account;
    }
}