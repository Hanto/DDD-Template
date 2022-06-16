package com.ddd.context.domain.model.product.events;// Created by jhant on 15/06/2022.

import com.ddd.context.application.common.Event;
import com.ddd.context.domain.model.product.Product;
import com.ddd.context.domain.model.product.ProductId;
import com.ddd.context.domain.model.product.ProductName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString(callSuper = true)
public class ProductCreatedEvent extends Event
{
    private ProductId productId;
    private ProductName productName;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public ProductCreatedEvent(ProductId productId, int version, ProductName productName)
    {
        super(productId.getId(), Product.class.getSimpleName(), version);
        this.productId = productId;
        this.productName = productName;
    }
}
