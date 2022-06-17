package com.ddd.context.domain.model.product.events;// Created by jhant on 15/06/2022.

import com.ddd.context.domain.common.Event;
import com.ddd.context.domain.model.account.Account;
import com.ddd.context.domain.model.product.Money;
import com.ddd.context.domain.model.product.PriceId;
import com.ddd.context.domain.model.product.ProductId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString(callSuper = true)
public class PriceCostAddedEvent extends Event
{
    private PriceId priceId;
    private Money money;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public PriceCostAddedEvent(ProductId pro, int version, PriceId priceId, Money money)
    {
        super(pro.getId(), Account.class.getSimpleName(), version);
        this.priceId = priceId;
        this.money = money;
    }
}
