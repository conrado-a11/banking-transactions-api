package com.coorporativo.banking_transactions_api.domain.model;


import java.util.Objects;

public final class Money {

    private final double amount;

    public  Money(double amount){

        if (amount < 0){
            throw new IllegalArgumentException("La cantidad no puede ser negativa");

        }
        this.amount = amount;

    }
    public static Money zero(){
        return new Money(0.0);
    }
    public double getAmount(){
        return amount;
    }
    public Money add(Money other){
        return new Money(this.amount + other.amount);
    }
    public Money subtract(Money other) {
        if (this.amount<other.amount){
            throw new IllegalArgumentException("Saldo insuficiente para esta operación");

        }
        return new Money(this.amount - other.amount);
    }
    public boolean isLessThan(Money other) {
        return this.amount< other.amount;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o ==null || getClass() !=o.getClass()) return false;
        Money money = ( Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public  int hashCode(){
        return  Objects.hash(amount);
    }


}
