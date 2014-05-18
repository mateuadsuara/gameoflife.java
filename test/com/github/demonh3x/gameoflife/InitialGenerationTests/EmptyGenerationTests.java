package com.github.demonh3x.gameoflife.InitialGenerationTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class EmptyGenerationTests {
    InitialGeneration generation;

    @Before
    public void setUp(){
        generation = new InitialGeneration(new DeadCellFactory());
    }

    @Test
    public void whenLookingAtALocation_ShouldBeADeadCell(){
        assertThat(
                generation.lookAt(new BidimensionalLocation(0, 0)),
                is(instanceOf(DeadCell.class))
        );
    }

    @Test
    public void whenLookingAtALocationTwice_ShouldBeTheSameCell(){
        final BidimensionalLocation location = new BidimensionalLocation(0, 0);
        Cell firstCellSeen = generation.lookAt(location);
        Cell secondCellSeen = generation.lookAt(location);

        assertThat(
                firstCellSeen,
                is(sameInstance(secondCellSeen))
        );
    }
}
