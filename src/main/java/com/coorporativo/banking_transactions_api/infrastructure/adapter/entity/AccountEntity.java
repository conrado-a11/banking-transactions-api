package com.coorporativo.banking_transactions_api.infrastructure.adapter.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List; //


@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    private String id;
    private String customerId;
    private double balance;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TransactionEntity> transactions = new ArrayList<>();

    public AccountEntity() {}

    public AccountEntity(String id, double balance, String customerId) {
        this.id = id;
        this.balance = balance;
        this.customerId = customerId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
