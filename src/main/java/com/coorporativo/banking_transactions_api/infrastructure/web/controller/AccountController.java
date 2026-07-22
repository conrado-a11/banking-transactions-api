package com.coorporativo.banking_transactions_api.infrastructure.web.controller;


import com.coorporativo.banking_transactions_api.application.dto.AccountDetailsDto;
import com.coorporativo.banking_transactions_api.application.dto.CreateAccountCommand;
import com.coorporativo.banking_transactions_api.application.dto.DepositMoneyCommand;
import com.coorporativo.banking_transactions_api.application.dto.WithdrawMoneyCommand;
import com.coorporativo.banking_transactions_api.application.port.CreateAccountUseCase;
import com.coorporativo.banking_transactions_api.application.port.DepositMoneyUseCase;
import com.coorporativo.banking_transactions_api.application.port.GetAccountDetailsUseCase;
import com.coorporativo.banking_transactions_api.application.port.WithdrawMoneyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final DepositMoneyUseCase depositMoneyUseCase;
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
    private final GetAccountDetailsUseCase getAccountDetailsUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase,
                             DepositMoneyUseCase depositMoneyUseCase,
                             WithdrawMoneyUseCase withdrawMoneyUseCase,
                             GetAccountDetailsUseCase getAccountDetailsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.depositMoneyUseCase = depositMoneyUseCase;
        this.withdrawMoneyUseCase = withdrawMoneyUseCase;
        this.getAccountDetailsUseCase = getAccountDetailsUseCase;
    }
    @GetMapping
    public ResponseEntity<String>healthCheck(){
        return ResponseEntity.ok("ok");
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

}
