package com.banco.banking_api.infrastructure.adapter.out.mapper;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.infrastructure.adapter.out.persistence.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .owner(account.getOwner())
                .balance(account.getBalance())
                .build();
    }

    public static Account toDomain(AccountEntity entity) {
        Account account = new Account();
        account.setId(entity.getId());
        account.setOwner(entity.getOwner());
        account.setBalance(entity.getBalance());
        return account;
    }
}