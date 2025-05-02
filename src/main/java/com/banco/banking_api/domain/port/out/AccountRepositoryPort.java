package com.banco.banking_api.domain.port.out;

import com.banco.banking_api.domain.model.Account;

public interface AccountRepositoryPort {
    void save(Account account);
}