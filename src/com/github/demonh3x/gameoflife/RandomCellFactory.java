package com.github.demonh3x.gameoflife;

public class RandomCellFactory implements CellFactory {
    @Override
    public Cell create() {
        final int rand = (int) (Math.random() * 2);
        return rand == 0 ? new AliveCell(): new DeadCell();
    }
}
