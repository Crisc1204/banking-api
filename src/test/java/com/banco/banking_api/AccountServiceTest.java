package com.banco.banking_api;

import com.banco.banking_api.application.service.AccountService;
import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    @Test
    void shouldReturnAccountById() {
        AccountRepositoryPort repo = mock(AccountRepositoryPort.class);
        AccountService service = new AccountService(repo);

        Account account = new Account();
        account.setId(1L);
        account.setOwner("Magnus");
        account.setBalance(new BigDecimal("1000"));

        when(repo.findById(1L)).thenReturn(Optional.of(account));

        Optional<Account> result = service.getById(1L);
        assertTrue(result.isPresent());
        assertEquals("Magnus", result.get().getOwner());
    }
}