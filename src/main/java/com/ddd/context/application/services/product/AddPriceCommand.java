package com.ddd.context.application.services.product;// Created by jhant on 16/06/2022.

import com.ddd.context.application.common.Command;
import com.ddd.context.application.common.SelfValidatingObject;
import com.ddd.context.domain.model.product.*;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value @EqualsAndHashCode(callSuper = false)
public class AddPriceCommand extends SelfValidatingObject<CreateProductCommand> implements Command
{
    @NotNull ProductId productId;
    @NotNull PriceId priceId;
    @NotNull BrandId brandId;
    @NotNull DateInterval dateInterval;
    @NotNull int priority;
    @NotNull Money money;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public AddPriceCommand(long productId, long priceId, long brandId, LocalDateTime startDate,
        LocalDateTime endDate, int priority, BigDecimal money, String currency)
    {
        this.productId = new ProductId(productId);
        this.priceId = new PriceId(priceId);
        this.brandId = new BrandId(brandId);
        this.dateInterval = new DateInterval(startDate, endDate);
        this.priority = priority;
        this.money = new Money(money, currency);
    }
}
