package com.ddd.context.application.product;// Created by jhant on 16/06/2022.

import com.ddd.context.application.ports.Query;
import com.ddd.context.application.ports.SelfValidatingObject;
import com.ddd.context.domain.model.product.ProductId;
import com.ddd.context.domain.model.product.ProductProyection;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value @EqualsAndHashCode(callSuper = false)
public class FindProductQuery  extends SelfValidatingObject<FindProductQuery> implements Query<ProductProyection>
{
    @NotNull ProductId productId;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public FindProductQuery(Long productId)
    {
        this.productId = new ProductId(productId);
        this.validateSelf();
    }
}
