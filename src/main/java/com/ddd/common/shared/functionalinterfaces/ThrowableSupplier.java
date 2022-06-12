package com.ddd.common.shared.functionalinterfaces;

@FunctionalInterface
public interface ThrowableSupplier<T>
{
    T get() throws Exception;
}