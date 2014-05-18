package com.github.demonh3x.gameoflife.AdjacentCellsTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AllAliveCellsButTheAdjacentOnesTests {
    IterableCounter<Cell> aliveNeighbours;

    @Before
    public void setUp(){
        final BidimensionalLocation center = new BidimensionalLocation(1, 1);

        final InitialGeneration allAliveButAdjacent = deadCellsAt(new BidimensionalLocation[]{
                new BidimensionalLocation(0, 0), new BidimensionalLocation(0, 1), new BidimensionalLocation(0, 2),
                new BidimensionalLocation(1, 0),                                  new BidimensionalLocation(1, 2),
                new BidimensionalLocation(2, 0), new BidimensionalLocation(2, 1), new BidimensionalLocation(2, 2),
        });

        aliveNeighbours = new IterableCounter<Cell>(
                new IterableFilter<Cell>(
                        new IterableFilter.Filter<Cell>() {
                            @Override
                            public Boolean accept(Cell c) {
                                return c instanceof AliveCell;
                            }
                        },
                        new AdjacentCells(allAliveButAdjacent, center)
                )
        );
    }

    private InitialGeneration deadCellsAt(BidimensionalLocation[] locations) {
        final HashMap<Location, Cell> deadCells = new HashMap<Location, Cell>();

        for (BidimensionalLocation l : locations)
            deadCells.put(l, new DeadCell());

        return new InitialGeneration(new AliveCellFactory(), deadCells);
    }

    @Test
    public void shouldNotHaveAnyCell(){
        assertThat(aliveNeighbours.count(), is(0));
    }
}
