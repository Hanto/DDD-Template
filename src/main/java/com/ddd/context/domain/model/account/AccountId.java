package com.ddd.context.domain.model.account;// Created by jhant on 14/06/2022.

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode @ToString
@Getter
public class AccountId
{
    @NonNull private Long id;
}