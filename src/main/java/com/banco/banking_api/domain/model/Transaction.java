package com.banco.banking_api.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String type;
    private LocalDateTime date;
}