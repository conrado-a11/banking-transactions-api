package com.coorporativo.banking_transactions_api.domain.model;

public class Transaction {


    private final  String id;
    private final String type;
    private final Money amount;

    public Transaction(String id, String type, Money amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Money getAmount() {
        return amount;
    }
}
