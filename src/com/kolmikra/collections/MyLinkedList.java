package com.kolmikra.collections;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements ILinkedList<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    public MyLinkedList() {
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private void linkBefore(E e, Node<E> current) {
        final Node<E> previous = current.prev;
        final Node<E> newNode = new Node<>(previous, e, current);
        current.prev = newNode;
        if (previous == null)
            first = newNode;
        else
            previous.next = newNode;
        size++;
    }

    private Node<E> getNode(int index) {
        if (index < (size >> 1)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.prev;
                return x;
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        linkLast(element);
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size)
            linkLast(element);
        else
            linkBefore(element, getNode(index));
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).item;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> remItem = getNode(index);

        E element = remItem.item;
        Node<E> next = remItem.next;
        Node<E> prev = remItem.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            remItem.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            remItem.next = null;
        }

        remItem.item = null;
        size--;
        return element;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = getNode(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        if (arr.length < size) {
            arr = (T[]) java.lang.reflect.Array.newInstance(
                    arr.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = arr;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (arr.length > size)
            arr[size] = null;

        return arr;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(this);
    }

    private static class ListIterator<E> implements Iterator<E>{
        private Node<E> current;
        private Node<E> previous;
        private int nextIndex;
        private MyLinkedList<E> ourList;

        public ListIterator(MyLinkedList<E> list) {
            ourList = list;
            reset();
        }
        public void reset(){
            current = ourList.first;
            previous = null;
            nextIndex = 0;
        }
        @Override
        public boolean hasNext() {
            return nextIndex < ourList.size();
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            previous = current;
            current = current.next;
            nextIndex++;
            return previous.item;
        }
    }

    public String toString() {
        Iterator<E> it = iterator();
        if (size == 0)
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while(it.hasNext()){
            E e = it.next();
            if(e != last.item) {
                sb.append(e);
                sb.append(',').append(' ');
            }
        }
        sb.append(last.item);
        return sb.append(']').toString();
    }
}
