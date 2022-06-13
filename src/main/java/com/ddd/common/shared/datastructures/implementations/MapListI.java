package com.ddd.common.shared.datastructures.implementations;

import com.ddd.common.shared.datastructures.DefaultMap;

import java.util.Collection;
import java.util.List;

/**@author Ivan Delgado Huerta*/
public interface MapListI<K, V> extends DefaultMap<K, List<V>>
{
    void add(K key, V value);
    void add(K key, Collection<V> values);
}