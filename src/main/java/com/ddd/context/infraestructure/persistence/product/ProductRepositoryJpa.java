package com.ddd.context.infraestructure.persistence.product;// Created by jhant on 04/06/2022.

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepositoryJpa extends EntityGraphJpaRepository<ProductEntity, Long>
{
    @Query(nativeQuery = true, value = "values next value for PRODUCT_ID_SEQUENCE")
    long getNextId();
}