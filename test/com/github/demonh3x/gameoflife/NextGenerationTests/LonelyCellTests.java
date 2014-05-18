package com.github.demonh3x.gameoflife.NextGenerationTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LonelyCellTests {
    Generation nextGeneration;

    @Before
    public void setUp(){
        HashMap<Location, Cell> aliveCells = new HashMap<Location, Cell>();
        aliveCells.put(new BidimensionalLocation(0, 0), new AliveCell());
        nextGeneration = new NextGeneration(new InitialGeneration(new DeadCellFactory(), aliveCells));
    }

    @Test
    public void shouldBeDeadInTheNextGeneration(){
        assertDead(new BidimensionalLocation(0, 0));
    }

    private void assertDead(BidimensionalLocation location) {
        assertThat(nextGeneration.lookAt(location), is(instanceOf(DeadCell.class)));
    }
}
