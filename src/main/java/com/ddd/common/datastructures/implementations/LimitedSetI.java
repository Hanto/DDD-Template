package com.ddd.common.datastructures.implementations;

import com.ddd.common.datastructures.DefaultSet;

/**@author Ivan Delgado Huerta*/
public interface LimitedSetI<K> extends DefaultSet<K>
{
    void addUpdate(K value);
}
