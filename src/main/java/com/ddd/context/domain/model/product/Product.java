package com.ddd.context.domain.model.product;// Created by jhant on 15/06/2022.

import com.ddd.context.application.common.Event;
import com.ddd.context.domain.common.DomainAggregateRoot;
import com.ddd.context.domain.model.product.events.PriceAddedEvent;
import com.ddd.context.domain.model.product.events.PriceCostAddedEvent;
import com.ddd.context.domain.model.product.events.ProductCreatedEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) @ToString
public class Product extends DomainAggregateRoot
{
    @EqualsAndHashCode.Include
    @Getter private ProductId productId;
    @Getter private ProductName productName;
    private final Prices prices = new Prices();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public Product(ProductId productId, ProductName name)
    {
        ProductCreatedEvent event = new ProductCreatedEvent(productId, getNextVersion(), name);

        applyNewEvent(event);
    }

    public Product(List<Event> events)
    {   applyAllEvents(events); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void addPrice(PriceId priceId, BrandId brandId, DateInterval dateInterval, int priority, Money money)
    {
        PriceAddedEvent event = new PriceAddedEvent(productId, getNextVersion(), priceId, brandId, dateInterval, priority, money);

        applyNewEvent(event);
    }

    public void addCost(PriceId priceId, Money money)
    {
        PriceCostAddedEvent event = new PriceCostAddedEvent(productId, getNextVersion(), priceId, money);

        applyNewEvent(event);
    }

    @Override
    public String getId()
    {   return productId.getId().toString(); }

    // EVENT HANDLERS:
    //--------------------------------------------------------------------------------------------------------

    private void apply(ProductCreatedEvent event)
    {
        this.productId = event.getProductId();
        this.productName = event.getProductName();
    }

    private void apply(PriceAddedEvent event)
    {
        Price price = new Price(event.getPriceId(), event.getBrandId(), event.getDateInterval(), event.getPriority(), event.getMoney());
        prices.addPrices(List.of(price));
    }

    private void apply(PriceCostAddedEvent event)
    {
        Price price = prices.getPrice(event.getPriceId());
        price.addCost(event.getMoney());
    }
}