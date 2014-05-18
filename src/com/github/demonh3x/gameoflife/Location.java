package com.github.demonh3x.gameoflife;

import java.util.Iterator;

public interface Location {
    public Iterator<Location> adjacent();
    public int hashCode();
    public boolean equals(Object obj);
}
