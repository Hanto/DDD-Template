package com.ddd.context.infraestructure.persistence.product;// Created by jhant on 04/06/2022.

import com.ddd.context.domain.model.product.Price;
import com.ddd.context.domain.model.product.ProductId;
import com.ddd.context.domain.model.product.ProductName;
import com.ddd.context.domain.model.product.ProductProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component @Scope(SCOPE_SINGLETON)
@RequiredArgsConstructor
public class ProductMapper
{
    @Autowired private final PriceMapper priceMapper;

    // FROM ENTITY:
    //--------------------------------------------------------------------------------------------------------

    public ProductProyection fromFullEntity(ProductEntity productEntity)
    {
        ProductId productId         = new ProductId(productEntity.getProductId());
        ProductName name            = new ProductName(productEntity.getShortName(), productEntity.getLongName());
        Collection<Price> priceList = productEntity.getPrices().stream().map(priceMapper::fromEntity).toList();

        return new ProductProyection(productId, name, productEntity.getVersion(), priceList);
    }

    public ProductProyection fromBaseEntity(ProductEntity productEntity)
    {
        ProductId productId         = new ProductId(productEntity.getProductId());
        ProductName name            = new ProductName(productEntity.getShortName(), productEntity.getLongName());

        return new ProductProyection(productId, name, productEntity.getVersion());
    }

    // FROM DOMAIN:
    //--------------------------------------------------------------------------------------------------------

    public ProductEntity fromDomain(ProductProyection product)
    {
        List<PriceEntity>priceEntities = product.getPriceList().stream()
            .map(priceMapper::fromDomain).toList();
        priceEntities.forEach(price -> price.setProductId(product.getProductId().getId()));

        return ProductEntity.builder()
            .productId(product.getProductId().getId())
            .shortName(product.getProductName().getShortName())
            .longName(product.getProductName().getLongName())
            .prices(priceEntities)
            .version(product.getVersion())
            .build();
    }
}