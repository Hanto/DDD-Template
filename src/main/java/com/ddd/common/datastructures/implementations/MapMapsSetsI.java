package com.ddd.common.datastructures.implementations;

import com.ddd.common.datastructures.DefaultMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**@author Ivan Delgado Huerta*/
public interface MapMapsSetsI<K,T,V> extends DefaultMap<K, Map<T, Set<V>>>
{
    void put(K id1, T id2, Set<V> value);
    void add(K id1, T id2, V value);
    Set<V> get(K id1, T id2);
    Collection<Set<V>> values(K id1);
    boolean containsKey(K id1, T id2);
}