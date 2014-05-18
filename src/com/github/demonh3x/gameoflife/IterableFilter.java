package com.github.demonh3x.gameoflife;

import java.util.Iterator;

public class IterableFilter<T> implements Iterable<T>{
    public static interface Filter<T> {
        public Boolean accept(T obj);
    }

    private static class IteratorFilter<T> implements Iterator<T>{
        private final Iterator<T> origin;
        private final Filter<T> filter;

        public IteratorFilter(Iterator<T> origin, Filter<T> filter) {
            this.origin = origin;
            this.filter = filter;
        }

        private T nextT = null;
        private boolean nextTServed = true;

        @Override
        public boolean hasNext() {
            advanceToNext();

            return !nextTServed;
        }

        private void advanceToNext() {
            while (nextTServed && this.origin.hasNext()){
                T t = origin.next();
                if (filter.accept(t)){
                    nextT = t;
                    nextTServed = false;
                }
            }
        }

        @Override
        public T next() {
            advanceToNext();

            nextTServed = true;
            return nextT;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final Iterable<T> origin;
    private final Filter<T> filter;

    public IterableFilter(Filter<T> filter, Iterable<T> origin){
        this.origin = origin;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorFilter<T>(this.origin.iterator(), this.filter);
    }
}
