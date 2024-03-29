package com.ddd.context.infraestructure.persistence.product;// Created by jhant on 04/06/2022.

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.ddd.context.domain.model.product.ProductName.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Entity @DynamicInsert @DynamicUpdate @Table(name = "PRODUCT")
@Cache(region = ProductEntity.PRODUCT_CACHE_REGION, usage = READ_WRITE)
@NamedEntityGraphs
({
    @NamedEntityGraph
    (   name = ProductEntity.GRAPH_PRODUCT_ALL, attributeNodes =
        {
            @NamedAttributeNode(value = "prices")
        }
    )
})
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductEntity implements Persistable<Long>
{
    public static final String GRAPH_PRODUCT_ALL = "Product.All";
    public static final String PRODUCT_CACHE_REGION = "Products";

    // ENTITY:
    //--------------------------------------------------------------------------------------------------------

    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @Setter(AccessLevel.NONE)
    private long productId;

    @Column(name = "SHORT_NAME", nullable = false)
    @NotNull @Size(min = SHORTNAME_MIN_SIZE, max = SHORTNAME_MAX_SIZE)
    private String shortName;

    @Column(name = "LONG_NAME", nullable = false)
    @NotNull @Size(min = LONGNAME_MIN_SIZE, max = LONGNAME_MAX_SIZE)
    private String longName;

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage= READ_WRITE)
    private List<PriceEntity>prices = new ArrayList<>();

    // VERSION:
    //--------------------------------------------------------------------------------------------------------

    @Version
    private Integer version = 0;

    // PERSISTABLE (for fast inserts:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Long getId()
    {   return productId; }

    @Transient
    private boolean isNew = false;
}