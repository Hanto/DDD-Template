package com.ddd.common.datastructures;

import lombok.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**@author Ivan Delgado Huerta*/
public interface DefaultSet<K> extends Set<K>
{
    Set<K>getSet();

    @Override default int size()
    {   return getSet().size(); }

    @Override default boolean isEmpty()
    {   return getSet().isEmpty(); }

    @Override default boolean contains(Object o)
    {   return getSet().contains(o); }

    @Override default Iterator<K> iterator()
    {   return getSet().iterator(); }

    @Override default void forEach(Consumer<? super K> action)
    {   getSet().forEach(action); }

    @Override default Object[] toArray()
    {   return getSet().toArray(); }

    @Override default <T> T[] toArray(T @NonNull [] a)
    {   //noinspection SuspiciousToArrayCall
        return getSet().toArray(a); }

    @Override default boolean add(K k)
    {   return getSet().add(k); }

    @Override default boolean remove(Object o)
    {   return getSet().remove(o); }

    @Override default boolean containsAll(@NonNull Collection<?> c)
    {   return getSet().containsAll(c); }

    @Override default boolean addAll(@NonNull Collection<? extends K> c)
    {   return getSet().addAll(c); }

    @Override default boolean retainAll(@NonNull Collection<?> c)
    {   return getSet().retainAll(c); }

    @Override default boolean removeAll(@NonNull Collection<?> c)
    {   return getSet().removeAll(c); }

    @Override default boolean removeIf(Predicate<? super K> filter)
    {   return getSet().removeIf(filter); }

    @Override default void clear()
    {   getSet().clear(); }

    @Override default Spliterator<K> spliterator()
    {   return getSet().spliterator(); }

    @Override default Stream<K> stream()
    {   return getSet().stream(); }

    @Override default Stream<K> parallelStream()
    {   return getSet().parallelStream(); }
}
