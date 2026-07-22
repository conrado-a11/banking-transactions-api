package com.coorporativo.banking_transactions_api.application.dto;

public class CreateAccountCommand {

    private final String customerId;
    private final double initialBalance;

    public CreateAccountCommand(String customerId, double initialBalance) {
        this.customerId = customerId;
        this.initialBalance = initialBalance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    @Override
    public String toString() {
        return "CreateAccountCommand{" +
                "customerId='" + customerId + '\'' +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
