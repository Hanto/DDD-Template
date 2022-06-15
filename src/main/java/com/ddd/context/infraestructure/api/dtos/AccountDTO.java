package com.ddd.context.infraestructure.api.dtos;// Created by jhant on 15/06/2022.

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = false)
@Setter @Getter
public class AccountDTO extends RepresentationModel<AccountDTO>
{
    private Long accountId;
    private String clientId;
    private BigDecimal balance;
    private int version;
}