package com.github.demonh3x.gameoflife.NextGenerationTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FiveCellsTests {
    public static class Loc {
        public static BidimensionalLocation XY(Integer x, Integer y){
            return new BidimensionalLocation(x, y);
        }
    }

    Generation nextGeneration;

    @Before
    public void setUp(){
        /**
         * Pattern: Glider
         *
         *   #
         * # #
         *  ##
         */
        final HashMap<Location, Cell> aliveCells = aliveLocations(
                                            Loc.XY(0, 2),
                Loc.XY(1, 0),               Loc.XY(1, 2),
                              Loc.XY(2, 1), Loc.XY(2, 2)
        );
        nextGeneration = new NextGeneration(new InitialGeneration(new DeadCellFactory(), aliveCells));
    }

    private HashMap<Location, Cell> aliveLocations(Location ... aliveLocations) {
        HashMap<Location, Cell> aliveCells = new HashMap<Location, Cell>();
        for (Location l : aliveLocations){
            aliveCells.put(l, new AliveCell());
        }
        return aliveCells;
    }

    @Test
    public void shouldCrawlToTheNextState(){
        /**
         *  #
         *   ##
         *  ##
         */
        assertAlive(
                              Loc.XY(0, 1),
                                            Loc.XY(1, 2), Loc.XY(1, 3),
                              Loc.XY(2, 1), Loc.XY(2, 2)
        );

        assertDead(
                Loc.XY(0, 0),               Loc.XY(0, 2), Loc.XY(0, 3),
                Loc.XY(1, 0), Loc.XY(1, 1),
                Loc.XY(2, 0),                             Loc.XY(2, 3)
        );
    }


    private void assertAlive(BidimensionalLocation ... aliveLocations) {
        for (Location l : aliveLocations){
            assertThat(nextGeneration.lookAt(l), is(instanceOf(AliveCell.class)));
        }
    }

    private void assertDead(BidimensionalLocation ... deadLocations) {
        for (Location l : deadLocations){
            assertThat(nextGeneration.lookAt(l), is(instanceOf(DeadCell.class)));
        }
    }
}
