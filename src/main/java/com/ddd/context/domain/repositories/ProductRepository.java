package com.ddd.context.domain.repositories;// Created by jhant on 04/06/2022.

import com.ddd.context.domain.model.product.ProductId;
import com.ddd.context.domain.model.product.ProductProyection;

import java.util.Collection;

public interface ProductRepository
{
    Collection<ProductProyection> loadProducts(int page, int pageSize);
    ProductProyection loadProduct(ProductId productId);

    void saveProduct(ProductProyection product);
    void saveNewProduct(ProductProyection product);
}