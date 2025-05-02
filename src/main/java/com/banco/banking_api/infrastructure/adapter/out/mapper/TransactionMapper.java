package com.banco.banking_api.infrastructure.adapter.out.mapper;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.infrastructure.adapter.out.persistence.TransactionEntity;

public class TransactionMapper {

    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .date(transaction.getDate())
                .build();
    }

    public static Transaction toDomain(TransactionEntity entity) {
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setAccountId(entity.getAccountId());
        transaction.setAmount(entity.getAmount());
        transaction.setType(entity.getType());
        transaction.setDate(entity.getDate());
        return transaction;
    }
}
