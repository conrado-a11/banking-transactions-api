package com.coorporativo.banking_transactions_api.infrastructure.adapter.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private String id;
    private String type;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public TransactionEntity(){}

    public TransactionEntity(String id, String type, double amount, AccountEntity account) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}
