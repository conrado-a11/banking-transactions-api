package com.coorporativo.banking_transactions_api.domain.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message){
        super (message);
    }
}
