package com.banco.banking_api;

import com.banco.banking_api.application.service.AccountService;
import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.out.AccountRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepositoryPort accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = Account.builder()
                .id(1L)
                .owner("Magnus")
                .balance(new BigDecimal("1000.00"))
                .build();
    }

    @Test
    void createAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        Account result = accountService.create(testAccount);

        assertNotNull(result);
        assertEquals("Magnus", result.getOwner());
        verify(accountRepository).save(testAccount);
    }

    @Test
    void getAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));

        Optional<Account> result = accountService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getAllAccounts() {
        List<Account> accounts = Arrays.asList(testAccount);
        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> result = accountService.getAll();

        assertEquals(1, result.size());
        verify(accountRepository).findAll();
    }

    @Test
    void updateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        Account result = accountService.update(testAccount);

        assertNotNull(result);
        verify(accountRepository).save(testAccount);
    }

    @Test
    void deleteAccount() {
        doNothing().when(accountRepository).deleteById(1L);

        accountService.delete(1L);

        verify(accountRepository).deleteById(1L);
    }
}