package com.banco.banking_api.infrastructure.adapter.in;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.in.CreateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestParam Account customerId) {
        createAccountUseCase.createAccount(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping
    public Account create(@RequestBody Account acc) {
        return createAccountUseCase.createAccount(acc);
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return createAccountUseCase.getAccount(id);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDto dto) {
        createAccountUseCase.transfer(dto.fromId(), dto.toId(), dto.amount());
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> transactions(@PathVariable Long id) {
        return createAccountUseCase.getTransactions(id);
    }
}