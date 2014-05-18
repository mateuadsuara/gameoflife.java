package com.github.demonh3x.gameoflife;

public class GameOfLife {
    private Generation current;

    public GameOfLife(Generation initial){
        current = initial;
    }

    public Generation tick(){
        this.current = new NextGeneration(current);
        return this.current;
    }
}
