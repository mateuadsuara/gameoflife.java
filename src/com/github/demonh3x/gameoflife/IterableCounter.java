package com.github.demonh3x.gameoflife;

import java.util.Iterator;

public class IterableCounter<T> implements Iterable<T> {
    private final Iterable<T> iterable;

    public IterableCounter(Iterable<T> iterable){
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }

    private Integer count = null;

    public Integer count(){
        if (count == null) count = doCount();
        return count;
    }

    private Integer doCount() {
        Integer n = 0;
        for (T t : this.iterable) n++;
        return n;
    }
}
