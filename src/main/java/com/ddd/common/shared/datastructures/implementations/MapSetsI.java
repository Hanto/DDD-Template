package com.ddd.common.shared.datastructures.implementations;

import com.ddd.common.shared.datastructures.DefaultMap;

import java.util.Collection;
import java.util.Set;

/**@author Ivan Delgado Huerta*/
public interface MapSetsI<K, V> extends DefaultMap<K, Set<V>>
{
    void add(K key, V value);
    void add(K key, Collection<V> values);
}
