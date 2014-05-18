package com.github.demonh3x.gameoflife;

public class DeadCellFactory implements CellFactory {
    @Override
    public Cell create() {
        return new DeadCell();
    }
}
