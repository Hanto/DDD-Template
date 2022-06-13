package com.ddd.common.shared.datastructures.implementations;

import lombok.Getter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**@author Ivan Delgado Huerta*/
public class MapSets<K, V> implements MapSetsI<K, V>
{
    @Getter private final Map<K, Set<V>>map;
    private final Supplier<Set<V>> setCreator;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------------------

    public MapSets(Supplier<Map<K, Set<V>>>mapCreator, Supplier<Set<V>>setCreator)
    {
        this.map = mapCreator.get();
        this.setCreator = setCreator;
    }

    @Override public void add(K key, V value)
    {
        map.computeIfAbsent(key, k -> setCreator.get());
        Set<V> set = map.get(key);
        set.add(value);
    }

    @Override public void add(K key, Collection<V> values)
    {
        map.computeIfAbsent(key, k -> setCreator.get());
        Set<V> set = map.get(key);
        set.addAll(values);
    }
}
