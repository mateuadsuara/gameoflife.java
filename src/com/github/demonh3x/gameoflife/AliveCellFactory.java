package com.github.demonh3x.gameoflife;

public class AliveCellFactory implements CellFactory {
    @Override
    public Cell create() {
        return new AliveCell();
    }
}
