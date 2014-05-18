package com.github.demonh3x.gameoflife.AdjacentCellsTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AllDeadCellsButTheAdjacentOnesTests {
    IterableCounter<Cell> aliveNeighbours;

    @Before
    public void setUp(){
        final BidimensionalLocation center = new BidimensionalLocation(1, 1);

        final InitialGeneration allDeadButAdjacent = aliveCellsAt(new BidimensionalLocation[]{
                new BidimensionalLocation(0, 0), new BidimensionalLocation(0, 1), new BidimensionalLocation(0, 2),
                new BidimensionalLocation(1, 0),                                  new BidimensionalLocation(1, 2),
                new BidimensionalLocation(2, 0), new BidimensionalLocation(2, 1), new BidimensionalLocation(2, 2),
        });

        aliveNeighbours = new IterableCounter<Cell>(new IterableFilter<Cell>(
                new IterableFilter.Filter<Cell>() {
                    @Override
                    public Boolean accept(Cell c) {
                        return c instanceof AliveCell;
                    }
                },
                new AdjacentCells(allDeadButAdjacent, center)
        ));
    }

    private InitialGeneration aliveCellsAt(BidimensionalLocation[] locations) {
        final HashMap<Location, Cell> aliveCells = new HashMap<Location, Cell>();

        for (BidimensionalLocation l : locations)
            aliveCells.put(l, new AliveCell());

        return new InitialGeneration(new DeadCellFactory(), aliveCells);
    }

    @Test
    public void shouldHaveAllTheNeighbourCells(){
        assertThat(aliveNeighbours.count(), is(8));
    }
}
