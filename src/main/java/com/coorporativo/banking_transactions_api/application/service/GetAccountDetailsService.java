package com.coorporativo.banking_transactions_api.application.service;


import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.TransactionDto;
import com.coorporativo.banking_transactions_api.application.port.GetAccountDetailsUseCase;
import com.coorporativo.banking_transactions_api.domain.exception.AccountNotFoundException;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GetAccountDetailsService implements GetAccountDetailsUseCase {


    private final AccountRepository accountRepository;

    public GetAccountDetailsService(AccountRepository accountRepository){
        this.accountRepository =accountRepository;

    }

    @Override
    public AccountDetailsDto execute(String accountId) {
        Account account = accountRepository.findById(new AccountId(accountId))
                .orElseThrow(()->new AccountNotFoundException("Cuenta no encontrada con ID " + accountId));

        return mapToDto(account);
    }
    private AccountDetailsDto mapToDto(Account account){
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
