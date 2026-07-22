package com.coorporativo.banking_transactions_api.infrastructure.adapter.repository;



import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.domain.model.Money;
import com.coorporativo.banking_transactions_api.domain.model.Transaction;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import com.coorporativo.banking_transactions_api.infrastructure.adapter.entity.AccountEntity;
import com.coorporativo.banking_transactions_api.infrastructure.adapter.entity.TransactionEntity;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountRepositoryAdapter implements AccountRepository {

    private final SpringDataAccountRepository springDataAccountRepository;

    public AccountRepositoryAdapter(SpringDataAccountRepository springDataAccountRepository) {
        this.springDataAccountRepository = springDataAccountRepository;
    }


    @Override
    public Account save(Account account) {
        // Usamos el constructor vacío y seteamos los datos uno a uno para evitar errores
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId().getValue());
        entity.setCustomerId(account.getCustomerId());
        entity.setBalance(account.getBalance().getAmount());

        List<TransactionEntity> transactionEntities = account.getTransaction().stream()
                .map(t -> {
                    TransactionEntity tEntity = new TransactionEntity();
                    tEntity.setId(t.getId());
                    tEntity.setType(t.getType());
                    tEntity.setAmount(t.getAmount().getAmount());
                    tEntity.setAccount(entity);
                    return tEntity;
                })
                .collect(Collectors.toList());

        entity.setTransactions(transactionEntities);

        AccountEntity savedEntity = springDataAccountRepository.save(entity);

        return mapToDomain(savedEntity);
    }


    @Override
    public Optional<Account> findById(AccountId id) {
        return springDataAccountRepository.findById(id.getValue())
                .map(this:: mapToDomain);
    }

    private Account mapToDomain(AccountEntity entity){
        List<Transaction> domainTransactions = entity.getTransactions().stream()
                .map(t-> new Transaction(t.getId(),t.getType(), new Money(t.getAmount())))
                .collect(Collectors.toList());

        return new Account(
                new AccountId(entity.getId()),
                entity.getCustomerId(),
                new Money(entity.getBalance()),
                domainTransactions);

    }
}
