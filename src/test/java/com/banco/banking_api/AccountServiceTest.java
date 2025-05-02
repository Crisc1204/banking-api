package com.banco.banking_api;

import com.banco.banking_api.domain.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepo;
    @Mock TransactionRepository transactionRepo;
    @InjectMocks
    AccountServiceImpl service;

    @Test
    void transfer_ShouldMoveFunds_WhenBalanceIsSufficient() {
        Account from = new Account(1L, "Juan", 500.0);
        Account to = new Account(2L, "Ana", 200.0);
        when(accountRepo.findById(1L)).thenReturn(Optional.of(from));
        when(accountRepo.findById(2L)).thenReturn(Optional.of(to));

        service.transfer(1L, 2L, 100.0);

        verify(accountRepo).save(from);
        verify(accountRepo).save(to);
        verify(transactionRepo).save(any());
    }
}