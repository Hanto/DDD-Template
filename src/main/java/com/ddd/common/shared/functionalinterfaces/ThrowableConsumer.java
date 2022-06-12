package com.ddd.common.shared.functionalinterfaces;

/**@author Ivan Delgado Huerta*/
@FunctionalInterface
public interface ThrowableConsumer<T>
{
    void accept(T t) throws Exception;
}
