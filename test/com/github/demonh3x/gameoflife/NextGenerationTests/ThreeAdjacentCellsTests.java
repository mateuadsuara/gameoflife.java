package com.github.demonh3x.gameoflife.NextGenerationTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ThreeAdjacentCellsTests {
    Generation nextGeneration;
    final BidimensionalLocation loc1 = new BidimensionalLocation(0, 0);
    final BidimensionalLocation loc2 = new BidimensionalLocation(1, 0);
    final BidimensionalLocation loc3 = new BidimensionalLocation(0, 1);

    @Before
    public void setUp(){
        final HashMap<Location, Cell> aliveCells = aliveLocations(new Location[]{loc1, loc2, loc3});
        nextGeneration = new NextGeneration(new InitialGeneration(new DeadCellFactory(), aliveCells));
    }

    private HashMap<Location, Cell> aliveLocations(Location[] aliveLocations) {
        HashMap<Location, Cell> aliveCells = new HashMap<Location, Cell>();
        for (Location l : aliveLocations){
            aliveCells.put(l, new AliveCell());
        }
        return aliveCells;
    }

    @Test
    public void shouldSurvive(){
        assertAlive(loc1);
        assertAlive(loc2);
        assertAlive(loc3);
    }

    @Test
    public void shouldCreateAChild(){
        assertAlive(new BidimensionalLocation(1, 1));
    }

    private void assertAlive(BidimensionalLocation location) {
        assertThat(nextGeneration.lookAt(location), is(instanceOf(AliveCell.class)));
    }
}
