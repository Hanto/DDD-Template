package com.ddd.context.application.product;// Created by jhant on 16/06/2022.

import com.ddd.context.application.ports.Query;
import com.ddd.context.application.ports.SelfValidatingObject;
import com.ddd.context.domain.model.product.BrandId;
import com.ddd.context.domain.model.product.Price;
import com.ddd.context.domain.model.product.ProductId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Value @EqualsAndHashCode(callSuper = false)
public class FindPriceAtQuery extends SelfValidatingObject<FindPriceAtQuery> implements Query<Price>
{
    @NotNull ProductId productId;
    @NotNull BrandId brandId;
    @NotNull LocalDateTime dateTime;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public FindPriceAtQuery(long productId, long brandId, LocalDateTime localDateTime)
    {
        this.productId = new ProductId(productId);
        this.brandId = new BrandId(brandId);
        this.dateTime = localDateTime;
        this.validateSelf();;
    }
}