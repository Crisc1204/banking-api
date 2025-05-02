package com.banco.banking_api;

import com.banco.banking_api.application.service.TransactionService;
import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.out.TransactionRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepositoryPort transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testTransaction = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(new BigDecimal("200.00"))
                .type("DEPOSIT")
                .build();
    }

    @Test
    void createTransaction() {
        Transaction transactionWithoutDate = Transaction.builder()
                .accountId(1L)
                .amount(new BigDecimal("200.00"))
                .type("DEPOSIT")
                .build();

        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction saved = invocation.getArgument(0);
            return saved.toBuilder().id(1L).build();
        });
        Transaction result = transactionService.createTransaction(transactionWithoutDate);
        assertNotNull(result.getDate(), "La fecha debería estar establecida");
        assertEquals(LocalDateTime.now().getDayOfYear(), result.getDate().getDayOfYear());
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void getTransactionsByAccount() {
        List<Transaction> transactions = Arrays.asList(testTransaction);
        when(transactionRepository.findByAccountId(1L)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactionsByAccount(1L);

        assertEquals(1, result.size());
        verify(transactionRepository).findByAccountId(1L);
    }

    @Test
    void getAllTransactions() {
        List<Transaction> transactions = Arrays.asList(testTransaction);
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(1, result.size());
        verify(transactionRepository).findAll();
    }

    @Test
    void deleteTransaction() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.deleteTransaction(1L);

        verify(transactionRepository).deleteById(1L);
    }
}