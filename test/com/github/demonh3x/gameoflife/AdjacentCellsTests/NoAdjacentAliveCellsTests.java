package com.github.demonh3x.gameoflife.AdjacentCellsTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class NoAdjacentAliveCellsTests {
    IterableCounter<Cell> aliveNeighbours;

    @Before
    public void setUp(){
        final BidimensionalLocation center = new BidimensionalLocation(1, 1);
        final HashMap<Location, Cell> aliveCells = new HashMap<Location, Cell>();
        aliveCells.put(center, new AliveCell());
        final InitialGeneration generation = new InitialGeneration(new DeadCellFactory(), aliveCells);
        aliveNeighbours = new IterableCounter<Cell>(new IterableFilter<Cell>(
                new IterableFilter.Filter<Cell>() {
                    @Override
                    public Boolean accept(Cell c) {
                        return c instanceof AliveCell;
                    }
                },
                new AdjacentCells(generation, center)
        ));
    }

    @Test
    public void shouldNotHaveAnyCell(){
        assertThat(aliveNeighbours.count(), is(0));
    }
}
