package com.github.demonh3x.gameoflife.ui;

import com.github.demonh3x.gameoflife.*;

public class Main {
    public static void main (String ... args) throws InterruptedException {
        final GameOfLife gameOfLife = new GameOfLife(new InitialGeneration(new RandomCellFactory()));
        BidimensionalLocation[][] locationsWindow = constructWorldWindow(20, 77);

        for (int i = 0; i < 100; i++){
            final String screen = toString(gameOfLife.tick(), locationsWindow);
            System.out.println(screen);
            Thread.sleep(10);
        }
    }

    private static BidimensionalLocation[][] constructWorldWindow(int rows, int cols){
        BidimensionalLocation[][] locations = new BidimensionalLocation[rows][cols];

        for (int x = 0; x < rows; x++){
            for (int y = 0; y < cols; y++){
                locations[x][y] = new BidimensionalLocation(x, y);
            }
        }

        return locations;
    }

    private static String toString(Generation gen, BidimensionalLocation[][] locations) {
        String s = "\n\n\n\n";

        for (BidimensionalLocation[] rows : locations) {
            s += " ";
            for (BidimensionalLocation location : rows) {
                final Cell cell = gen.lookAt(location);
                s += cell instanceof AliveCell ? '#' : ' ';
            }
            s += "\n";
        }

        return s;
    }
}
