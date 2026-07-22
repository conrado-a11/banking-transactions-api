package com.coorporativo.banking_transactions_api.domain.exception;

public class InsufficientResourcesException extends RuntimeException{
    public InsufficientResourcesException(String message){
        super(message);
    }

}
