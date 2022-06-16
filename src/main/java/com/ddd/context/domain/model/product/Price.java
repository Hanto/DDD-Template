package com.ddd.context.domain.model.product;// Created by jhant on 03/06/2022.

import com.ddd.context.domain.common.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) @ToString
public class Price extends DomainEntity
{
    @EqualsAndHashCode.Include
    @Getter private PriceId priceId;
    @Getter private BrandId brandId;

    @Getter private DateInterval dateInterval;
    @Getter int priority;
    @Getter Money money;

    @Getter private final int version;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public Price(PriceId priceId, BrandId brandId, DateInterval dateInterval, int priority, Money money)
    {
        this.priceId = priceId; this.brandId = brandId; this.dateInterval = dateInterval;
        this.priority = priority; this.money = money; this.version = 0;
    }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public void addCost(Money moneyToAdd)
    {   money = money.plus(moneyToAdd); }

    public void modifyCost(Money modifiedMoney)
    {   money = modifiedMoney; }
}