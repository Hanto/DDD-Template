package com.ddd.context.domain.model.product;// Created by jhant on 03/06/2022.

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@EqualsAndHashCode @ToString
@Getter
public class Money
{
    BigDecimal cuantity;
    Currency currency;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public Money(BigDecimal cuantity, Currency currency)
    {
        this.cuantity = cuantity;
        this.currency = currency;
        validate();
    }

    public Money(BigDecimal cuantity, String currency)
    {
        this.cuantity = cuantity;
        this.currency = Currency.valueOf(currency);
        validate();
    }

    public Money(Float cuantity, String currency)
    {
        this.cuantity = BigDecimal.valueOf(cuantity);
        this.currency = Currency.valueOf(currency);
        validate();
    }

    public Money(Float cuantity, Currency currency)
    {
        this.cuantity = BigDecimal.valueOf(cuantity);
        this.currency = currency;
        validate();
    }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public Money plus(Money money)
    {
        if (money.getCurrency() != this.getCurrency())
            throw new IllegalArgumentException("Currency should be the same");

        BigDecimal result = this.getCuantity().add(money.getCuantity());

        return new Money(result, this.getCurrency());

    }

    public Money minus(Money money)
    {
        if (money.getCurrency() != this.getCurrency())
            throw new IllegalArgumentException("Currency should be the same");

        BigDecimal result = this.getCuantity().subtract(money.getCuantity());

        return new Money(result, this.getCurrency());
    }

    // CALCULATIONS:
    //--------------------------------------------------------------------------------------------------------

    @JsonIgnore
    public boolean isPositive()
    {   return cuantity.compareTo(BigDecimal.ZERO) >= 0; }

    // VALIDATION:
    //--------------------------------------------------------------------------------------------------------

    private void validate()
    {
        if (cuantity == null || currency == null)
            throw new IllegalArgumentException("Arguments cannot be null");
    }
}