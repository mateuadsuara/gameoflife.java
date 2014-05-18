package com.github.demonh3x.gameoflife;

import java.util.HashMap;
import java.util.Map;

public class InitialGeneration implements Generation {
    private final CellFactory undefinedFactory;
    private final Map<Location, Cell> cells;

    public InitialGeneration(CellFactory undefined){
        undefinedFactory = undefined;
        cells = new HashMap<Location, Cell>();
    }

    public InitialGeneration(CellFactory undefined, Map<Location, Cell> defined){
        undefinedFactory = undefined;
        cells = new HashMap<Location, Cell>();
        cells.putAll(defined);
    }

    public Cell lookAt(Location where){
        if (!cells.containsKey(where)){
            cells.put(where, undefinedFactory.create());
        }

        return cells.get(where);
    }
}
