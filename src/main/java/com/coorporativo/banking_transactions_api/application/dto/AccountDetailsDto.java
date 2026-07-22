package com.coorporativo.banking_transactions_api.application.dto;

import java.util.List;

public class AccountDetailsDto {
    private String id;
    private String customerId;
    private double balance;
    private List<TransactionDto>transaction;

    public AccountDetailsDto(String id, String customerId, double balance, List<TransactionDto> transaction) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
        this.transaction = transaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<TransactionDto> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionDto> transaction) {
        this.transaction = transaction;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountDetailsDto{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
                ", transaction=" + transaction +
                '}';
    }
}
