package com.ddd.common.datastructures.implementations;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**@author Ivan Delgado Huerta*/
public class MapMaps <K, T, V> implements MapMapsI<K, T, V>
{
    private final Map<K, Map<T, V>> rootMap;
    private final Supplier<Map<T, V>> mapCreator;

    @Override public Map<K, Map<T, V>> getMap()     {   return rootMap; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------------------

    public MapMaps (Supplier<Map<K, Map<T, V>>> mapMapsCreator, Supplier<Map<T, V>> mapCreator)
    {
        this.rootMap    = mapMapsCreator.get();
        this.mapCreator = mapCreator;
    }

    // NEW FEATURES:
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    public void put(K id1, T id2, V value)
    {
        rootMap.computeIfAbsent(id1, k -> mapCreator.get());
        Map<T, V> map = rootMap.get(id1);
        map.put(id2, value);
    }

    @Override
    public V get(K id1, T id2)
    {
        Map<T, V> map = get(id1);
        return map == null ? null: map.get(id2);
    }

    @Override
    public Collection<V> values(K id1)
    {   return get(id1).values(); }

    @Override
    public boolean containsKey(K id1, T id2)
    {
        Map<T, V> map = get(id1);
        return map != null && map.containsKey(id2);
    }

    protected V computeIfAbsent(K id1, T id2, Function<? super T, ? extends V> mappingFunction)
    {
        rootMap.computeIfAbsent(id1, k -> mapCreator.get());
        return rootMap.get(id1).computeIfAbsent(id2, mappingFunction);
    }
}
