package com.banco.banking_api.infrastructure.adapter.out.mapper;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.infrastructure.adapter.out.persistence.AccountJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapper {

    public AccountJpaEntity toEntity(Account account) {
        return new AccountJpaEntity(account.getId(), account.getCustomerId(), account.getBalance());
    }
}