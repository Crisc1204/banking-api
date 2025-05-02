package com.banco.banking_api.infrastructure.adapter.out.mapper;


import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {

    public Account toDomain(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .owner(entity.getOwner())
                .balance(entity.getBalance())
                .build();
    }

    public AccountEntity toEntity(Account domain) {
        return AccountEntity.builder()
                .id(domain.getId())
                .owner(domain.getOwner())
                .balance(domain.getBalance())
                .build();
    }
}