package com.ddd.context.domain.model.product.events;// Created by jhant on 15/06/2022.

import com.ddd.context.domain.common.Event;
import com.ddd.context.domain.model.product.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString(callSuper = true)
public class PriceAddedEvent extends Event
{
    private ProductId productId;
    private PriceId priceId;
    private BrandId brandId;
    private DateInterval dateInterval;
    private int priority;
    private Money money;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public PriceAddedEvent(ProductId productId, int version, PriceId priceId, BrandId brandId, DateInterval dateInterval, int priority, Money money)
    {
        super(productId.getId(), Product.class.getSimpleName(), version);
        this.productId = productId;
        this.priceId = priceId;
        this.brandId = brandId;
        this.dateInterval = dateInterval;
        this.priority = priority;
        this.money = money;
    }
}