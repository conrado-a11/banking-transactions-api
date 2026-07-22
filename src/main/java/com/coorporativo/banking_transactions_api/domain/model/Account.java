package com.coorporativo.banking_transactions_api.domain.model;

import com.coorporativo.banking_transactions_api.domain.exception.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Account {


    private final AccountId id;
    private final String customerId;
    private Money balance;
    private final List<Transaction> transaction;

    public Account(AccountId id, String customerId, Money initialBalance){
        this.id=id;
        this.customerId = customerId;
        this.balance= initialBalance;
        this.transaction = new ArrayList<>();

    }
    public Account(AccountId id, String customerId, Money balance, List<Transaction> transaction){
        this.id=id;
        this.customerId = customerId;
        this.balance = balance;
        this.transaction = new ArrayList<>(transaction);
    }
    //Regla de negocio :retirar  con validacion limpia
    public void witdraw(Money amount, String transationId){
        if(this.balance.isLessThan(amount)){
            throw new InsufficientResourcesException("Saldo insuficiente para este retiro");

        }
        this.balance = this.balance.subtract(amount);
        this.transaction.add(new Transaction(transationId, "WITHDRAW", amount ));

    }

    public void deposit(Money amount, String transactionId) {
        this.balance = this.balance.add(amount);
        this.transaction.add(new Transaction(transactionId, "DEPOSIT", amount));
    }

    //Getter inmutables o puros


    public AccountId getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Money getBalance() {
        return balance;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }


}
