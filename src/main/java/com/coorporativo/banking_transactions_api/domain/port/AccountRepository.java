package com.coorporativo.banking_transactions_api.domain.port;

import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.infrastructure.adapter.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(AccountId id);
    List<AccountEntity> findAll();
}
