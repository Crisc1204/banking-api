package com.banco.banking_api.infrastructure.adapter.in;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.in.TransactionUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions")
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    public TransactionController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionUseCase.createTransaction(transaction);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getByAccount(@PathVariable Long accountId) {
        return transactionUseCase.getTransactionsByAccount(accountId);
    }
}
