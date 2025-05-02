package com.banco.banking_api.infrastructure.adapter.in;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.in.AccountUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts")
public class AccountController {

    private final AccountUseCase useCase;

    public AccountController(AccountUseCase useCase) {
        this.useCase = useCase;
    }


    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.ok(useCase.create(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id) {
        return useCase.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Account> getAll() {
        return useCase.getAll();
    }

    @PutMapping
    public ResponseEntity<Account> update(@RequestBody Account account) {
        return ResponseEntity.ok(useCase.update(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}