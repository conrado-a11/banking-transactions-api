package com.coorporativo.banking_transactions_api.infrastructure.adapter.repository;

import com.coorporativo.banking_transactions_api.infrastructure.adapter.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, String> {
}
