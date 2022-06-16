package com.ddd.context.infraestructure.persistence.product;// Created by jhant on 03/06/2022.

import com.ddd.context.domain.model.product.ProductId;
import com.ddd.context.domain.model.product.ProductProyection;
import com.ddd.context.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs.named;
import static com.ddd.context.infraestructure.persistence.product.ProductEntity.GRAPH_PRODUCT_ALL;
import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class ProductAdapterJpa implements ProductRepository
{
    @Autowired private final PriceRepositoryJpa priceRepo;
    @Autowired private final ProductRepositoryJpa productRepo;
    @Autowired private final ProductMapper productMapper;
    @Autowired private final PriceMapper priceMapper;

    // PRODUCTS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public ProductProyection loadProduct(ProductId productId)
    {
        ProductEntity productEntity = productRepo.findById(productId.getId(), named(GRAPH_PRODUCT_ALL))
            .orElseThrow(() -> new IllegalArgumentException(format("No product exists with the id: %s", productId.getId())));

        return productMapper.fromFullEntity(productEntity);
    }

    @Override
    public Collection<ProductProyection>loadProducts(int page, int pageSize)
    {
        List<ProductEntity> list = productRepo.findAll(PageRequest.of(0, pageSize)).getContent();

        return list.stream()
            .map(productMapper::fromBaseEntity).toList();
    }

    @Override
    public void saveProduct(ProductProyection product)
    {
        ProductEntity entity = productMapper.fromDomain(product);
        productRepo.save(entity);
    }

    @Override
    public void saveNewProduct(ProductProyection product)
    {
        ProductEntity entity = productMapper.fromDomain(product);
        entity.setNew(true);
        productRepo.save(entity);
    }
}