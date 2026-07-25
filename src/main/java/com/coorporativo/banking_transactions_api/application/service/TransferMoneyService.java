package com.coorporativo.banking_transactions_api.application.service;


import com.coorporativo.banking_transactions_api.application.dto.TransferMoneyCommand;
import com.coorporativo.banking_transactions_api.application.port.TransferMoneyUseCase;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.model.AccountId;
import com.coorporativo.banking_transactions_api.domain.model.Money;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;




@Service
public class TransferMoneyService implements TransferMoneyUseCase {

    private final AccountRepository accountRepository;

    public TransferMoneyService(AccountRepository accountRepository){
        this.accountRepository =accountRepository;
    }

    @Override
    public void execute(TransferMoneyCommand command) {
        //1. Buscamos la cuenta de origen pasando el ID que viene en el comando
        Account sourceAccount = accountRepository.findById(new AccountId(command.getSourceAccountId()))
                .orElseThrow(()-> new RuntimeException("cuenta de origen no encontrada"));
        //buscamos la cuenta de destino de la misma forma
        Account targetAccount =accountRepository.findById(new AccountId(command.getTargetAccountId()))
                .orElseThrow(()-> new RuntimeException("cuenta de destino no encontrada"));

        //generamos ID unicos para las transacciones (retiro deposito)
        String withdrawTxId = java.util.UUID.randomUUID().toString();
        String depositTxId = java.util.UUID.randomUUID().toString();


        //ejecutamos la lógica de negocio(retirar y depositar)
        sourceAccount.withdraw(new Money(command.getAmount()), withdrawTxId);
        targetAccount.deposit(new Money(command.getAmount()), depositTxId);


        //aunque el metodo sea void, esta linea altera H2 y guarda el dinero
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

    }
}



