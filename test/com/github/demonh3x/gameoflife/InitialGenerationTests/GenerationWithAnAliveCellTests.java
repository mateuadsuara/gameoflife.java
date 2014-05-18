package com.github.demonh3x.gameoflife.InitialGenerationTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class GenerationWithAnAliveCellTests {
    InitialGeneration generation;
    Location aliveCellLocation;
    Cell aliveCell;

    @Before
    public void setUp(){
        aliveCellLocation = new BidimensionalLocation(0, 0);
        aliveCell = new AliveCell();

        HashMap<Location, Cell> definedCells = new HashMap<Location, Cell>();
        definedCells.put(aliveCellLocation, aliveCell);

        generation = new InitialGeneration(new DeadCellFactory(), definedCells);
    }

    @Test
    public void whenLookingAtTheAliveCellsLocation_ShouldBeTheAliveCell(){
        assertThat(
                generation.lookAt(aliveCellLocation),
                is(sameInstance(aliveCell))
        );
    }

    @Test
    public void whenLookingAtTheAliveCellsLocation_ByAnotherLocationInstance_ShouldBeTheAliveCell(){
        assertThat(
                generation.lookAt(new BidimensionalLocation(0, 0)),
                is(sameInstance(aliveCell))
        );
    }

    @Test
    public void whenLookingToAnotherLocation_ShouldBeADeadCell(){
        assertThat(
                generation.lookAt(new BidimensionalLocation(1, 0)),
                is(instanceOf(DeadCell.class))
        );
    }
}
