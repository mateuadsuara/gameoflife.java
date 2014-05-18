package com.github.demonh3x.gameoflife;

import java.util.Iterator;

public class AdjacentCells implements Iterable<Cell> {
    private final Generation generation;
    private final Location center;

    public AdjacentCells(Generation generation, Location center) {
        this.generation = generation;
        this.center = center;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new AdjacentCellsIterator(generation, center.adjacent());
    }

    private static class AdjacentCellsIterator implements Iterator<Cell> {
        private final Generation generation;
        private final Iterator<Location> adjacent;

        public AdjacentCellsIterator(Generation generation, Iterator<Location> adjacent){
            this.generation = generation;
            this.adjacent = adjacent;
        }

        @Override
        public boolean hasNext() {
            return adjacent.hasNext();
        }

        @Override
        public Cell next() {
            return generation.lookAt(adjacent.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
