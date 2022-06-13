package com.ddd.common.shared.datastructures.implementations;

import com.ddd.common.shared.datastructures.DefaultSet;

/**@author Ivan Delgado Huerta*/
public interface LimitedSetI<K> extends DefaultSet<K>
{
    void addUpdate(K value);
}
