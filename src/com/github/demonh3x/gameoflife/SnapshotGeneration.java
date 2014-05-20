package com.github.demonh3x.gameoflife;

import java.util.HashMap;

public class SnapshotGeneration implements Generation {
    private HashMap<Location, Cell> cells;
    private CellFactory unsavedFactory;

    public SnapshotGeneration(Generation from, Location[] where, CellFactory unsavedFactory){
        this.unsavedFactory = unsavedFactory;
        this.cells = new HashMap<Location, Cell>(where.length);
        for (Location l : where)
            this.cells.put(l, from.lookAt(l));
    }

    @Override
    public Cell lookAt(Location where) {
        if (!cells.containsKey(where)){
            cells.put(where, unsavedFactory.create());
        }

        return cells.get(where);
    }
}
