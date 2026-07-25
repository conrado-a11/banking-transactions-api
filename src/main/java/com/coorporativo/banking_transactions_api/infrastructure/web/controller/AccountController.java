package com.coorporativo.banking_transactions_api.infrastructure.web.controller;


import com.coorporativo.banking_transactions_api.application.dto.*;
import com.coorporativo.banking_transactions_api.application.port.*;
import com.coorporativo.banking_transactions_api.domain.model.Account;
import com.coorporativo.banking_transactions_api.domain.port.AccountRepository;
import com.coorporativo.banking_transactions_api.infrastructure.adapter.entity.AccountEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final DepositMoneyUseCase depositMoneyUseCase;
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
    private final GetAccountDetailsUseCase getAccountDetailsUseCase;
    private final AccountRepository accountRepository;
    private final TransferMoneyUseCase transferMoneyUseCase; // <--- Agrégalo arriba




    public AccountController(CreateAccountUseCase createAccountUseCase,
                             DepositMoneyUseCase depositMoneyUseCase,
                             WithdrawMoneyUseCase withdrawMoneyUseCase,
                             GetAccountDetailsUseCase getAccountDetailsUseCase,
                             AccountRepository accountRepository,
                             TransferMoneyUseCase transferMoneyUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.depositMoneyUseCase = depositMoneyUseCase;
        this.withdrawMoneyUseCase = withdrawMoneyUseCase;
        this.getAccountDetailsUseCase = getAccountDetailsUseCase;
        this.accountRepository = accountRepository;
        this.transferMoneyUseCase = transferMoneyUseCase;// <--- Y aquí
    }
    @GetMapping
    public ResponseEntity<List<AccountEntity>>getAllAccount(){
        List<AccountEntity> accounts = accountRepository.findAll(); // O .findAll(), .run(), mira cómo se llama el método dentro de GetAccountDetailsUseCase
        return ResponseEntity.ok(accounts);
    }
    @PostMapping
    public ResponseEntity<AccountDetailsDto> createAccount(@RequestBody CreateAccountCommand command){
        AccountDetailsDto response =  createAccountUseCase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountDetailsDto> depositMoney(@PathVariable String id, @RequestBody DepositMoneyCommand commandBody){
        //Combinamos el ID de la URL con el monto del cuerpo
        DepositMoneyCommand command = new DepositMoneyCommand(id,commandBody.getAmount());
        AccountDetailsDto response = depositMoneyUseCase.execute(command);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountDetailsDto> withdrawMoney(@PathVariable String id, @RequestBody WithdrawMoneyCommand commadBody){
        //Combinamos el ID de la URL con el monto del cuerpo
        WithdrawMoneyCommand command= new WithdrawMoneyCommand(id, commadBody.getAmount());
        AccountDetailsDto response = withdrawMoneyUseCase.execute(command);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDetailsDto> getAccountDetails(@PathVariable String id){
        AccountDetailsDto response  = getAccountDetailsUseCase.execute(id);
        return  ResponseEntity.ok(response);

    }
    @PostMapping("/{id}/transactions")
    public ResponseEntity<Void> transferMoney(@PathVariable String id, @RequestBody TransferMoneyCommand commandBody) {
        // Combinas el ID de origen de la URL con el destino y monto del cuerpo
        TransferMoneyCommand command = new TransferMoneyCommand(id, commandBody.getTargetAccountId(), commandBody.getAmount());

        // Ejecutas la lógica en tu caso de uso de negocio
        transferMoneyUseCase.execute(command);

        return ResponseEntity.ok().build();
    }


}
