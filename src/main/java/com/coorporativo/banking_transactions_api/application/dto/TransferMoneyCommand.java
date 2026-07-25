package com.coorporativo.banking_transactions_api.application.dto;

import com.coorporativo.banking_transactions_api.domain.model.Money;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class TransferMoneyCommand {

    private final String sourceAccountId;
    private final String targetAccountId;
    private final BigDecimal amount;



    public TransferMoneyCommand(String sourceAccountId, String targetAccountId, BigDecimal amount) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;

    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public BigDecimal getAmount() {return amount;
    }
}
