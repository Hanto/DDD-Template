package com.ddd.common.datastructures;

import lombok.NonNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**@author Ivan Delgado Huerta*/
public interface DefaultList<K> extends List<K>
{
    List<K>getList();

    // DECORATOR:
    //--------------------------------------------------------------------------------------------------------

    @Override default int size()
    {   return getList().size(); }

    @Override default boolean isEmpty()
    {   return getList().isEmpty(); }

    @Override default boolean contains(Object o)
    {   return getList().contains(o); }

    @Override default Iterator<K> iterator()
    {   return getList().iterator(); }

    @Override default void forEach(Consumer<? super K> action)
    {   getList().forEach(action); }

    @Override default Object[] toArray()
    {   return getList().toArray(); }

    @Override default <T> T[] toArray(T @NonNull [] a)
    {   //noinspection SuspiciousToArrayCall
        return getList().toArray(a);
    }

    @Override default boolean add(K k)
    {   return getList().add(k); }

    @Override default boolean remove(Object o)
    {   return getList().remove(o); }

    @SuppressWarnings("SlowListContainsAll")
    @Override default boolean containsAll(@NonNull Collection<?> c)
    {   return getList().containsAll(c); }

    @Override default boolean addAll(@NonNull Collection<? extends K> c)
    {   return getList().addAll(c); }

    @Override default boolean addAll(int index, @NonNull Collection<? extends K> c)
    {   return getList().addAll(index, c); }

    @Override default boolean removeAll(@NonNull Collection<?> c)
    {   return getList().removeAll(c); }

    @Override default boolean removeIf(Predicate<? super K> filter)
    {   return getList().removeIf(filter); }

    @Override default boolean retainAll(@NonNull Collection<?> c)
    {   return getList().retainAll(c); }

    @Override default void replaceAll(UnaryOperator<K> operator)
    {   getList().replaceAll(operator); }

    @Override default void sort(Comparator<? super K> c)
    {   getList().sort(c); }

    @Override default void clear()
    {   getList().clear(); }

    @Override default K get(int index)
    {   return getList().get(index); }

    @Override default K set(int index, K element)
    {   return getList().set(index, element); }

    @Override default void add(int index, K element)
    {   getList().add(index, element); }

    @Override default K remove(int index)
    {   return getList().remove(index); }

    @Override default int indexOf(Object o)
    {   return getList().indexOf(o); }

    @Override default int lastIndexOf(Object o)
    {   return getList().lastIndexOf(o); }

    @Override default ListIterator<K> listIterator()
    {   return getList().listIterator(); }

    @Override default ListIterator<K> listIterator(int index)
    {   return getList().listIterator(index); }

    @Override default List<K> subList(int fromIndex, int toIndex)
    {   return getList().subList(fromIndex, toIndex); }

    @Override default Spliterator<K> spliterator()
    {   return getList().spliterator(); }

    @Override default Stream<K> stream()
    {   return getList().stream(); }

    @Override default Stream<K> parallelStream()
    {   return getList().parallelStream(); }
}
