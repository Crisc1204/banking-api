package com.banco.banking_api.infrastructure.adapter.in;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.in.TransactionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions")
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    public TransactionController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @PostMapping
    @Operation(summary = "Crea nueva transacción")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionUseCase.createTransaction(transaction));
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Consulta las transacción por ID de cuenta")
    public ResponseEntity<List<Transaction>> getByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionUseCase.getTransactionsByAccount(accountId));
    }

    @GetMapping
    @Operation(summary = "Consulta todas las transacciones")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionUseCase.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar la transaccion por ID")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionUseCase.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}