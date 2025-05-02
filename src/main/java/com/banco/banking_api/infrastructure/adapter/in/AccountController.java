package com.banco.banking_api.infrastructure.adapter.in;

import com.banco.banking_api.domain.model.Account;
import com.banco.banking_api.domain.port.in.AccountUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "Crea nueva cuenta")
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.ok(useCase.create(account));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta la cuenta por ID")
    public ResponseEntity<Account> get(@PathVariable Long id) {
        return useCase.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Consulta todas las cuentas")
    public List<Account> getAll() {
        return useCase.getAll();
    }

    @PutMapping
    @Operation(summary = "Actualiza la cuenta")
    public ResponseEntity<Account> update(@RequestBody Account account) {
        return ResponseEntity.ok(useCase.update(account));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina la cuenta por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}