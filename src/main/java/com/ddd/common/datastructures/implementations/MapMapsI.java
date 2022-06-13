package com.ddd.common.datastructures.implementations;

import com.ddd.common.datastructures.DefaultMap;

import java.util.Collection;
import java.util.Map;

/**@author Ivan Delgado Huerta*/
public interface MapMapsI<K,T,V> extends DefaultMap<K, Map<T, V>>
{
    void put(K id1, T id2, V value);
    V get(K id1, T id2);
    Collection<V> values(K id1);
    boolean containsKey(K id1, T id2);
}