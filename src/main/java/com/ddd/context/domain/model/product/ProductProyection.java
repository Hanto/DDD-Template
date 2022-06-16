package com.ddd.context.domain.model.product;// Created by jhant on 16/06/2022.

import com.ddd.context.domain.model.product.events.PriceAddedEvent;
import com.ddd.context.domain.model.product.events.PriceCostAddedEvent;
import com.ddd.context.domain.model.product.events.ProductCreatedEvent;
import lombok.*;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Builder @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) @ToString
public class ProductProyection
{
    @EqualsAndHashCode.Include
    @Getter private ProductId productId;
    @Getter private ProductName productName;
    private final Prices prices = new Prices();
    @Getter private int version;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public ProductProyection(ProductId productId, ProductName productName, int version)
    {   this.productId = productId; this.productName = productName; this.version = version; }

    public ProductProyection(ProductId productId, ProductName productName, int version, Collection<Price>priceList)
    {
        this.productId = productId; this.productName = productName; this.version = version;
        prices.addPrices(priceList);
    }

    // READ:
    //--------------------------------------------------------------------------------------------------------

    public Collection<Price> getPriceList()
    {   return prices.getPriceList(); }

    // EVENT HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    public void apply(ProductCreatedEvent event)
    {
        this.productId = event.getProductId();
        this.productName = event.getProductName();
    }

    public void apply(PriceAddedEvent event)
    {
        Price price = new Price(event.getPriceId(), event.getBrandId(), event.getDateInterval(), event.getPriority(), event.getMoney());
        prices.addPrices(List.of(price));
    }

    public void apply(PriceCostAddedEvent event)
    {
        Price price = prices.getPrice(event.getPriceId());
        price.addCost(event.getMoney());
    }
}