package com.kolmikra.collections;

import java.util.Iterator;
import java.util.LinkedList;

public interface ILinkedList<E> extends Iterable<E> {
    int size();

    @Override
    Iterator<E> iterator();

    void add(E element);

    void add(int index, E element);

    void clear();

    E get(int index);

    int indexOf(E element);

    E remove(int index);

    E set(int index, E element);

    String toString();

    <T> T[] toArray(T[] arr);

    public Object[] toArray();

}
