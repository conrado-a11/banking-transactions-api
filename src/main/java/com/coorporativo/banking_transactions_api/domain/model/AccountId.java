package com.coorporativo.banking_transactions_api.domain.model;

import java.util.Objects;

public final class AccountId {
    private  final String value;

    public AccountId(String value){

        if (value == null || value.trim().isEmpty()){
            throw new IllegalArgumentException("El ID de la cuenta no puede estar vacío");

        }
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(value, accountId.value);

    }



}
