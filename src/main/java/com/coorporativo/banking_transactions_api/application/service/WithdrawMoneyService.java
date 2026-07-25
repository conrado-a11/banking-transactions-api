package com.coorporativo.banking_transactions_api.application.service;


import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.TransactionDto;
import com.coorporativo.banking_transactions_api.application.dto.WithdrawMoneyCommand;
import com.coorporativo.banking_transactions_api.application.port.WithdrawMoneyUseCase;
import com.coorporativo.banking_transactions_api.domain.exception.AccountNotFoundException;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.domain.model.Money;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WithdrawMoneyService implements WithdrawMoneyUseCase {

    private final AccountRepository accountRepository;

    public WithdrawMoneyService(AccountRepository accountRepository){
        this.accountRepository =accountRepository;

    }
    @Override
    public AccountDetailsDto execute(WithdrawMoneyCommand command) {
        Account account = accountRepository.findById(new AccountId(command.getAccountId()))
                .orElseThrow(()-> new AccountNotFoundException("Cuenta no encontrada con ID: " + command.getAccountId()));


        String transactionId = "tx-" + UUID.randomUUID().toString().substring(0,8);
        account.withdraw(new Money(command.getAmount()), transactionId);

        Account updatedAccount = accountRepository.save(account);
        return maptToDto(updatedAccount);

    }
    private AccountDetailsDto maptToDto(Account account){
        return new AccountDetailsDto(
                account.getId().getValue(),
                account.getCustomerId(),
                account.getBalance().getAmount(),
                account.getTransaction().stream()
                        .map(t-> new TransactionDto(t.getId(), t.getType(), t.getAmount().getAmount()))
                        .collect(Collectors.toList())

        );

    }
}
