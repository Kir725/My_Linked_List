package com.kolmikra;

import com.kolmikra.collections.MyLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);

        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        list.add(3,17);
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
        System.out.println(list.indexOf(5));
        System.out.println(list.get(4));
        list.set(4,60);
        Integer[] arr = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(arr));

        MyLinkedList<Long> myList = new MyLinkedList<>();
        LinkedList<Long> javaList = new LinkedList<>();
        long value = 0;
        while (myList.size() < 100000) {
            myList.add(value++);
            javaList.add(value++);
        }
        System.out.println("-------- Add --------");
        long start = System.nanoTime();
        for (int i = 0; i < 50000; i++) {
            myList.add(500L + i++);
        }
        long stop = System.nanoTime();
        System.out.println("myList: " + (stop - start) +" ns");
        start = System.nanoTime();
        for (int i = 0; i < 50000; i++) {
            javaList.add(500L + i++);
        }
        stop = System.nanoTime();
        System.out.println("javaList: " + (stop - start)+" ns");

        System.out.println("-------- Search --------");
        start = System.nanoTime();
        myList.get(55000);
        stop = System.nanoTime();
        System.out.println("myList: " + (stop - start)+" ns");
        start = System.nanoTime();
        javaList.get(55000);
        stop = System.nanoTime();
        System.out.println("javaList: " + (stop - start)+" ns");

        System.out.println("-------- Remove --------");
        start = System.nanoTime();
        myList.remove(80000);
        stop = System.nanoTime();
        System.out.println("myList: " + (stop - start)+" ns");
        start = System.nanoTime();
        javaList.remove(80000);
        stop = System.nanoTime();
        System.out.println("javaList: " + (stop - start)+" ns");
    }
}
