package com.banco.banking_api.domain.port.in;

import com.banco.banking_api.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountUseCase  {
    Account create(Account account);
    Optional<Account> getById(Long id);
    List<Account> getAll();
    Account update(Account account);
    void delete(Long id);
}