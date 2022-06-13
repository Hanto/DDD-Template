package com.ddd.common.datastructures.implementations;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**@author Ivan Delgado Huerta*/
public class MapMapsSets<K, T, V> implements MapMapsSetsI<K, T, V>
{
    private final Map<K, Map<T, Set<V>>> rootMap;
    private final Supplier<Map<T, Set<V>>> mapCreator;
    private final Supplier<Set<V>>setCreator;

    @Override public Map<K, Map<T, Set<V>>> getMap()     {   return rootMap; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------------------

    public MapMapsSets(
        Supplier<Map<K, Map<T, Set<V>>>>rootMapCreator,
        Supplier<Map<T, Set<V>>> mapCreator,
        Supplier<Set<V>> setCreator)
    {
        this.rootMap = rootMapCreator.get();
        this.mapCreator = mapCreator;
        this.setCreator = setCreator;
    }

    // NEW FEATURES:
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    public void put(K id1, T id2, Set<V> value)
    {
        Map<T, Set<V>> map = rootMap.computeIfAbsent(id1, k -> mapCreator.get());
        map.put(id2, value);
    }

    @Override
    public void add(K id1, T id2, V value)
    {
        Map<T, Set<V>> map = rootMap.computeIfAbsent(id1, k -> mapCreator.get());
        Set<V>set = map.computeIfAbsent(id2, k -> setCreator.get());
        set.add(value);
    }

    @Override
    public Set<V> get(K id1, T id2)
    {
        Map<T, Set<V>> map = get(id1);
        return map == null ? null : map.get(id2);
    }

    @Override
    public Collection<Set<V>> values(K id1)
    {   return get(id1).values(); }

    @Override
    public boolean containsKey(K id1, T id2)
    {
        Map<T, Set<V>> map = get(id1);
        return map != null && map.containsKey(id2);
    }
}
