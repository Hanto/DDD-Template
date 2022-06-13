package com.ddd.common.shared.datastructures.implementations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**@author Ivan Delgado Huerta*/
public class MapList<K, V> implements MapListI<K, V>
{
    private final Map<K, List<V>> rootMap;
    private final Supplier<List<V>> listCreator;

    @Override public Map<K, List<V>> getMap()           { return rootMap; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------------------

    public MapList(Supplier<Map<K, List<V>>> mapCreator, Supplier<List<V>> listCreator)
    {
        this.rootMap    = mapCreator.get();
        this.listCreator= listCreator;
    }

    // NEW FEATURES:
    //--------------------------------------------------------------------------------------------------------------------

    @Override public void add(K key, V value)
    {
        List<V> list = rootMap.computeIfAbsent(key, k -> listCreator.get() );
        list.add(value);
    }

    @Override public void add(K key, Collection<V> values)
    {
        List<V> list = rootMap.computeIfAbsent(key, k -> listCreator.get() );
        list.addAll(values);
    }
}