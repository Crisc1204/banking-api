package com.banco.banking_api.infrastructure.adapter.out.persistence.repository;

import com.banco.banking_api.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {}