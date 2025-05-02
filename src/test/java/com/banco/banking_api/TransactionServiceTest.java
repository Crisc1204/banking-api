package com.banco.banking_api;

import com.banco.banking_api.application.service.TransactionService;
import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.out.TransactionRepositoryPort;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {


    @Test
    void shouldCreateTransaction() {
        TransactionRepositoryPort repo = mock(TransactionRepositoryPort.class);
        TransactionService service = new TransactionService(repo);

        Transaction t = new Transaction();
        t.setAccountId(1L);
        t.setAmount(new BigDecimal("100.00"));
        t.setType("DEPOSIT");

        when(repo.save(any())).thenReturn(t);

        Transaction created = service.createTransaction(t);
        assertEquals("DEPOSIT", created.getType());
    }
}
