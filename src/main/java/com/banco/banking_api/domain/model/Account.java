package com.banco.banking_api.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    private Long id;
    private String owner;
    private BigDecimal balance;
}