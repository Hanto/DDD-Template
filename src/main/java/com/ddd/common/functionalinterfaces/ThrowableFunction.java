package com.ddd.common.functionalinterfaces;

/**@author Ivan Delgado Huerta*/
@FunctionalInterface
public interface ThrowableFunction<T, R>
{
    R apply(T t) throws Exception;
}
