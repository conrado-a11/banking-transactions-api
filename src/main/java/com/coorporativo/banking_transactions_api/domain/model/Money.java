package com.coorporativo.banking_transactions_api.domain.model;


import java.math.BigDecimal;
import java.util.Objects;


public class Money {

    private final double amount;
    private final BigDecimal value;

    // 1. Constructor para el BigDecimal (El que usa el Service)
    public Money(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.value = value;
        this.amount = value.doubleValue();
    }

    // 2. Constructor para el double (El que usan tus métodos internos como zero() y subtract())
    public Money(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.amount = amount;
        this.value = BigDecimal.valueOf(amount);
    }

    public static Money zero() {
        return new Money(0.0);
    }

    public double getAmount() {
        return amount;
    }

    // Método suma (Usado en el .add del depósito)
    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    // Método resta (Usado en el .subtract del retiro)
    public Money subtract(Money other) {
        if (this.amount < other.amount) {
            throw new IllegalArgumentException("Saldo insuficiente para esta operación");
        }
        return new Money(this.amount - other.amount);
    }

    public boolean isLessThan(Money other) {
        return this.amount < other.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
