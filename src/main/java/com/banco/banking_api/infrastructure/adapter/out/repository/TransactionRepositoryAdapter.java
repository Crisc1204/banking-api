package com.banco.banking_api.infrastructure.adapter.out.repository;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.out.TransactionRepositoryPort;
import com.banco.banking_api.infrastructure.adapter.out.mapper.TransactionMapper;
import com.banco.banking_api.infrastructure.adapter.out.persistence.TransactionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {
    private final TransactionJpaRepository jpaRepository;

    public TransactionRepositoryAdapter(TransactionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return TransactionMapper.toDomain(
                jpaRepository.save(TransactionMapper.toEntity(transaction))
        );
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return jpaRepository.findByAccountId(accountId)
                .stream()
                .map(TransactionMapper::toDomain)
                .collect(Collectors.toList());
    }
}
