package com.ddd.context.infraestructure.api.product;// Created by jhant on 16/06/2022.

import com.ddd.context.application.ports.CommandBus;
import com.ddd.context.application.ports.QueryBus;
import com.ddd.context.application.product.AddPriceCommand;
import com.ddd.context.application.product.CreateProductCommand;
import com.ddd.context.application.product.FindPriceAtQuery;
import com.ddd.context.application.product.FindProductQuery;
import com.ddd.context.domain.model.product.Price;
import com.ddd.context.domain.model.product.ProductProyection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value ="/api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@RequiredArgsConstructor @SuppressWarnings("all")
public class ProductController
{
    @Autowired private final CommandBus commandBus;
    @Autowired private final QueryBus queryBus;
    @Autowired private final ProductDTOAssembler productAssembler;
    @Autowired private final PriceDTOAssembler priceAssembler;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @PostMapping("/product/{id}")
    public void createProduct(
        @PathVariable Long id,
        @RequestParam(value = "shortname") String shortname,
        @RequestParam(value = "longname") String longname)
    {
        CreateProductCommand command = new CreateProductCommand(id, shortname, longname);
        commandBus.send(command);
    }

    @PostMapping("/product/{productId}/{priceId}")
    public void createPrice(
        @PathVariable Long productId,
        @PathVariable Long priceId,
        @RequestParam(value = "brandId") Long brandId,
        @RequestParam(value = "startDate")  @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
        @RequestParam(value = "endDate")  @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
        @RequestParam(value = "priority") int priority,
        @RequestParam(value = "money") BigDecimal money,
        @RequestParam(value = "currency") String currency)
    {
        AddPriceCommand command = new AddPriceCommand(productId, priceId, brandId, startDate, endDate, priority, money, currency);
        commandBus.send(command);
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable Long id)
    {
        FindProductQuery query = new FindProductQuery(id);
        ProductProyection proyection = queryBus.send(query);

        return productAssembler.toModel(proyection);
    }

    @GetMapping("/price/{productId}")
    public PriceDTO getPrice(
        @PathVariable Long productId,
        @NotNull @RequestParam Long brandId,
        @NotNull @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateTime)
    {
        FindPriceAtQuery query = new FindPriceAtQuery(productId, brandId, dateTime);
        Price price = queryBus.send(query);

        return priceAssembler.toModel(price);
    }
}