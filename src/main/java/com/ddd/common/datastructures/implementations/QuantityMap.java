package com.ddd.common.datastructures.implementations;

import java.util.HashMap;

/**@author Ivan Delgado Huerta*/
public class QuantityMap<T> extends HashMap<T, Integer>
{
	// MAIN:
    //--------------------------------------------------------------------------------------------------------------------
	
	public void add(T key)
	{
		Integer value = this.computeIfAbsent(key, k -> 0);
		this.put(key, ++value);
	}
	
	@Override public Integer get(Object key)
	{	return this.getOrDefault(key, 0); }
}