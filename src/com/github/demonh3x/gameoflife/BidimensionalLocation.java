package com.github.demonh3x.gameoflife;

import java.util.Iterator;

public class BidimensionalLocation implements Location {
    private final Integer x, y;

    public BidimensionalLocation(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public int hashCode(){
        return x * y;
    }

    public boolean equals(Object other){
        if (!(other instanceof BidimensionalLocation)){
            return false;
        }

        BidimensionalLocation otherLocation = (BidimensionalLocation) other;

        return this.x.equals(otherLocation.x) &&
               this.y.equals(otherLocation.y);
    }

    public BidimensionalLocation translate(BidimensionalLocation delta){
        return new BidimensionalLocation(x + delta.x, y + delta.y);
    }

    @Override
    public Iterator<Location> adjacent() {
        return new AdjacentIterator(this);
    }

    private static class AdjacentIterator implements Iterator<Location> {
        private static final BidimensionalLocation[] DELTAS = new BidimensionalLocation[]{
                new BidimensionalLocation(-1, -1), new BidimensionalLocation(-1, 0), new BidimensionalLocation(-1, 1),
                new BidimensionalLocation(0, -1),                                    new BidimensionalLocation(0, 1),
                new BidimensionalLocation(1, -1),  new BidimensionalLocation(1, 0),  new BidimensionalLocation(1, 1),
        };

        private Integer currentIndex = -1;
        private final BidimensionalLocation center;

        public AdjacentIterator(BidimensionalLocation center){
            this.center = center;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < (DELTAS.length -1);
        }

        @Override
        public Location next() {
            return center.translate(DELTAS[++currentIndex]);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
