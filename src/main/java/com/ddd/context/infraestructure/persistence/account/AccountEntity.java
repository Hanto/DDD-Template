package com.ddd.context.infraestructure.persistence.account;// Created by jhant on 14/06/2022.

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity @DynamicInsert @DynamicUpdate
@Table(name = "ACCOUNT")
@NoArgsConstructor @AllArgsConstructor @Builder
@Setter @Getter @ToString
public class AccountEntity
{
    // ENTITY:
    //--------------------------------------------------------------------------------------------------------

    @Id
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Column(name = "CLIENT_ID", nullable = false)
    @NotNull
    private String clientId;

    @Column(name = "MONEY", nullable = false)
    @NotNull
    private BigDecimal balance;

    @Column(name = "VERSION", nullable = false)
    @NotNull
    private Integer version;
}