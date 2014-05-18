package com.github.demonh3x.gameoflife.NextGenerationTests;

import com.github.demonh3x.gameoflife.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EmptyGenerationTests {
    Generation nextGeneration;

    @Before
    public void setUp(){
        nextGeneration = new NextGeneration(new InitialGeneration(new DeadCellFactory()));
    }

    @Test
    public void shouldNotHaveAnyCell(){
        assertDead(new BidimensionalLocation(0, 0));
        assertDead(new BidimensionalLocation(0, 1));
        assertDead(new BidimensionalLocation(1, 1));
    }

    private void assertDead(BidimensionalLocation location) {
        assertThat(nextGeneration.lookAt(location), is(instanceOf(DeadCell.class)));
    }
}
