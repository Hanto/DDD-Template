package com.ddd.common.datastructures.implementations;

import lombok.Getter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**@author Ivan Delgado Huerta*/
public class LimitedSet<K> implements LimitedSetI<K>
{
    @Getter private final Set<K>set;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------------------

    public LimitedSet(int maxSize)
    {
        set = Collections.newSetFromMap(new LinkedHashMap<>()
        {   @Override protected boolean removeEldestEntry(Map.Entry eldest) { return size() > maxSize; } });
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------------------

    @Override public void addUpdate(K value)
    {
        set.remove(value);
        set.add(value);
    }
}
