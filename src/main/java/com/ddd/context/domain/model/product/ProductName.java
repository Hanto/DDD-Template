package com.ddd.context.domain.model.product;// Created by jhant on 03/06/2022.

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@NoArgsConstructor
@EqualsAndHashCode @ToString
@Getter
public class ProductName
{
    private String shortName;
    private String longName;

    public static final int SHORTNAME_MIN_SIZE = 3;
    public static final int SHORTNAME_MAX_SIZE = 20;
    public static final int LONGNAME_MIN_SIZE = 3;
    public static final int LONGNAME_MAX_SIZE = 50;

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public ProductName(String shortName, String longName)
    {
        this.shortName = shortName;
        this.longName = longName;
        validate();
    }

    // VALIDATOR:
    //--------------------------------------------------------------------------------------------------------

    private void validate()
    {
        List<String>errors = new ArrayList<>();

        if (hasIncorrectName(shortName))
            errors.add(format("Shortname: %s, should have only alphanumerical characters", shortName));

        if (hasIncorrectSize(shortName, SHORTNAME_MIN_SIZE, SHORTNAME_MAX_SIZE))
            errors.add(format("Shortname: %s, should have between %s and %s characters", shortName, SHORTNAME_MIN_SIZE, SHORTNAME_MAX_SIZE));

        if (hasIncorrectName(longName))
            errors.add(format("Longname: %s, should have only alphanumerical characters", longName));

        if (hasIncorrectSize(longName, LONGNAME_MIN_SIZE, LONGNAME_MAX_SIZE))
            errors.add(format("Longname: %s, should have between %s and %s characters", longName, LONGNAME_MIN_SIZE, LONGNAME_MAX_SIZE));

        if (!errors.isEmpty())
            throw new IllegalArgumentException(String.join(", ", errors));
    }

    private boolean hasIncorrectName(String name)
    {   return !name.matches("[a-zA-z\\d\\s]*"); }

    private boolean hasIncorrectSize(String string, int minSize, int maxSize)
    {   return string.length() < minSize || string.length() > maxSize; }
}
