package com.github.demonh3x.gameoflife.ui;

import com.github.demonh3x.gameoflife.*;

public class Main {
    public static void main (String ... args) throws InterruptedException {
        final GameOfLife gameOfLife = new GameOfLife(new InitialGeneration(new RandomCellFactory()));

        for (int i = 0; i < 100; i++){
            final String screen = toString(gameOfLife.tick());
            System.out.println(screen);
            Thread.sleep(10);
        }
    }

    private static String toString(Generation gen) {
        String s = "\n\n\n\n";
        int rows = 20;
        int cols = 77;

        for (int x = 0; x < rows; x++){
            s += " ";
            for (int y = 0; y < cols; y++){
                final Cell cell = gen.lookAt(new BidimensionalLocation(x, y));
                s += cell instanceof AliveCell? '#': ' ';
            }
            s += "\n";
        }

        return s;
    }
}
