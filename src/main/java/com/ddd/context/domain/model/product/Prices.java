package com.ddd.context.domain.model.product;// Created by jhant on 03/06/2022.

import com.ddd.context.domain.common.DomainAggregate;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.String.format;

//Aggregate
@NoArgsConstructor @ToString
public class Prices extends DomainAggregate
{
    private final Set<Price>prices = new HashSet<>();

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public void removePrice(Price price)
    {   prices.remove(price); }

    public void addPrices(Collection<Price> collection)
    {   collection.forEach(this::addPrice); }

    public void addPrice(Price price)
    {
        if (prices.contains(price))
            throw new IllegalArgumentException(format("Price already exists: %s", price.getPriceId()));

        prices.add(price);
    }

    public Collection<Price> getPriceList()
    {   return Collections.unmodifiableSet(prices); }

    public Price getPriceNow(BrandId brand)
    {   return getPriceAt(LocalDateTime.now(), brand); }

    public Price getPriceAt(LocalDateTime dateTime, BrandId brand)
    {
        return prices.stream()
            .filter(price -> price.getBrandId().equals(brand))
            .filter(price -> price.getDateInterval().isInDateInTheInterval(dateTime))
            .max(Comparator.comparing(Price::getPriority))
            .orElseThrow(() -> new IllegalArgumentException(
                format("There are no prices for the requested parameters, date: %s brand: %s", dateTime, brand.getId())));
    }

    public Price getPrice(PriceId priceId)
    {
        return prices.stream()
            .filter(p -> p.getPriceId().equals(priceId))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(
                format("There is no price with the following id: %s", priceId.getId())));
    }
}