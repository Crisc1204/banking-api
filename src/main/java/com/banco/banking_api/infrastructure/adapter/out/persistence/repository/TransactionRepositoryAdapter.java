package com.banco.banking_api.infrastructure.adapter.out.persistence.repository;

import com.banco.banking_api.domain.model.Transaction;
import com.banco.banking_api.domain.port.out.TransactionRepositoryPort;
import com.banco.banking_api.infrastructure.adapter.out.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionJpaRepository jpaRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionMapper.toDomain(
                jpaRepository.save(transactionMapper.toEntity(transaction))
        );
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return jpaRepository.findByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(transactionMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}