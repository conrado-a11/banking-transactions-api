package com.coorporativo.banking_transactions_api.domain.port;

import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(AccountId id);
}
