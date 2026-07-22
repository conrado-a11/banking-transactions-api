package com.coorporativo.banking_transactions_api.application.service;

import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.CreateAccountCommand;
import com.coorporativo.banking_transactions_api.application.dto.TransactionDto;
import com.coorporativo.banking_transactions_api.application.port.CreateAccountUseCase;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.domain.model.Money;
import com.coorporativo.banking_transactions_api.domain.model.Transaction;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreateAccountService implements CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountService(AccountRepository accountRepository){
        this.accountRepository =accountRepository;
    }

    @Override
    public AccountDetailsDto execute(CreateAccountCommand command) {
        String generatedId = "acc-" + UUID.randomUUID().toString().substring(0,8);

        Account account = new Account(
                new AccountId(generatedId),
                command.getCustomerId(),
                new Money(command.getInitialBalance())
        );

        Account saveAccount = accountRepository.save(account);
        return mapToDto(saveAccount);
    }


    private AccountDetailsDto mapToDto(Account account) {
        return new AccountDetailsDto(
                account.getId().getValue(),
                account.getCustomerId(),
                account.getBalance().getAmount(),
                account.getTransaction().stream()
                        .map(t -> new TransactionDto(t.getId(), t.getType(), t.getAmount().getAmount()))
                        .collect(Collectors.toList())
        );

    }
}
