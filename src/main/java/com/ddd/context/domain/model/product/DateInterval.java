package com.ddd.context.domain.model.product;// Created by jhant on 04/06/2022.

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@NoArgsConstructor
@EqualsAndHashCode @ToString
@Getter
public class DateInterval
{
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public DateInterval(LocalDateTime startDate, LocalDateTime endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        validate();
    }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public boolean isInDateInTheInterval(LocalDateTime date)
    {   return startDate.isBefore(date) && endDate.isAfter(date); }

    // VALIDATION:
    //--------------------------------------------------------------------------------------------------------

    private void validate()
    {
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Start date cannot be after End date");
    }
}
