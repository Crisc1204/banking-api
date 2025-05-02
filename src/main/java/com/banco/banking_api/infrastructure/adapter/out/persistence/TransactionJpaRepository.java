package com.banco.banking_api.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByAccountId(Long accountId);
}
