package com.coorporativo.banking_transactions_api.application.service;

import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.DepositMoneyCommand;
import com.coorporativo.banking_transactions_api.application.dto.TransactionDto;
import com.coorporativo.banking_transactions_api.application.port.DepositMoneyUseCase;
import com.coorporativo.banking_transactions_api.domain.exception.AccountNotFoundException;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.domain.model.Money;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepositMoneyService implements DepositMoneyUseCase {

        private final AccountRepository accountRepository;

        public DepositMoneyService(AccountRepository accountRepository){
                this.accountRepository = accountRepository;
        }

    @Override
    public AccountDetailsDto execute(DepositMoneyCommand command) {
        Account account = accountRepository.findById(new AccountId(command.getAccountId()))
                .orElseThrow(()-> new AccountNotFoundException("Cuenta no encontrada con ID" + command.getAccountId()));


        String transactionId = "tx-" + UUID.randomUUID().toString().substring(0,8);
        account.deposit(new Money(command.getAmount()), transactionId);

        Account updatedAccount =accountRepository.save(account);
        return mapToDto(updatedAccount);

        
        
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
