package com.ddd.common.functionalinterfaces;

@FunctionalInterface
public interface ThrowableSupplier<T>
{
    T get() throws Exception;
}