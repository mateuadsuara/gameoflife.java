package com.github.demonh3x.gameoflife;

import java.util.HashMap;

public class NextGeneration implements Generation {
    private final Generation previous;

    public NextGeneration(Generation previous){
        this.previous = previous;
    }

    private HashMap<Location, Cell> cache = new HashMap<Location, Cell>();
    @Override
    public Cell lookAt(Location where) {
        if (!cache.containsKey(where))
            cache.put(where, doLookAt(where));

        return cache.get(where);
    }

    private Cell doLookAt(Location where) {
        Integer aliveNeighbours = adjacentAliveCellsAt(where);

        if (aliveNeighbours < 2 || aliveNeighbours > 3){
            final Cell previousCell = previous.lookAt(where);
            return (previousCell instanceof DeadCell)? previousCell: new DeadCell();
        }

        if (aliveNeighbours == 2){
            return previous.lookAt(where);
        }

        return new AliveCell();
    }

    private Integer adjacentAliveCellsAt(Location where) {
        return new IterableCounter<Cell>(
                new IterableFilter<Cell>(
                        new IterableFilter.Filter<Cell>() {
                            @Override
                            public Boolean accept(Cell c) {
                                return c instanceof AliveCell;
                            }
                        },
                        new AdjacentCells(previous, where)
                )
        ).count();
    }
}
