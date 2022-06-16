package com.ddd.context.application.product;// Created by jhant on 16/06/2022.

import com.ddd.context.application.ports.Command;
import com.ddd.context.application.ports.SelfValidatingObject;
import com.ddd.context.domain.model.product.ProductId;
import com.ddd.context.domain.model.product.ProductName;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value @EqualsAndHashCode(callSuper = false)
public class CreateProductCommand extends SelfValidatingObject<CreateProductCommand> implements Command
{
    @NotNull ProductId productId;
    @NotNull ProductName productName;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public CreateProductCommand(Long productId, String shortName, String longName)
    {
        this.productId = new ProductId(productId);
        this.productName = new ProductName(shortName, longName);
        this.validateSelf();
    }
}