package com.github.demonh3x.gameoflife.ui;

import com.github.demonh3x.gameoflife.*;

import java.util.ArrayList;

public class Main {
    public static void main (String ... args) throws InterruptedException {
        final int generationsToRender = args.length >= 1? Integer.parseInt(args[0]) : 200;
        final int msPerFrame = args.length >= 2? Integer.parseInt(args[1]): 200;
        final int framesPerClean = args.length >= 3? Integer.parseInt(args[2]): 10;

        Generation gen = new InitialGeneration(new RandomCellFactory());
        BidimensionalLocation[][] locationsWindow = constructWorldWindow(20, 77);
        Location[] locations = flatten(locationsWindow);

        ArrayList<Integer> renderTimes = new ArrayList<Integer>();
        for (int i = 0; i <= generationsToRender; i++){
            long start, end;
            start = System.currentTimeMillis();

            final String screen = toString(gen, locationsWindow);

            if (i % framesPerClean == 0){
                gen = new SnapshotGeneration(gen, locations, new DeadCellFactory());
            }
            gen = new NextGeneration(gen);

            end = System.currentTimeMillis();
            long renderTime = end - start;
            renderTimes.add((int) renderTime);

            long maxFps = 1000 / renderTime;
            long actualFps = 1000 / (renderTime > msPerFrame? renderTime : msPerFrame);

            System.out.println(screen + String.format(
                    "Frame: %s/%s, RenderTime: %sms, FrameTime: %sms FPS: %s(actual)/%s(potential)",
                    i, generationsToRender, renderTime, msPerFrame, actualFps, maxFps)
            );

            long msToWait = msPerFrame - renderTime;
            if (msToWait > 0)
                Thread.sleep(msToWait);
        }

        Statistics s = new Statistics(renderTimes);

        System.out.println(String.format(
                "Average render time: %sms, Min: %sms, Max: %sms",
                s.average, s.min, s.max
        ));
    }

    private static class Statistics {
        public final long average, sum, min, max;

        private Statistics(ArrayList<Integer> renderTimes) {
            long sum = 0, min = Long.MAX_VALUE, max = Long.MIN_VALUE;
            for (int i : renderTimes){
                sum += i;
                if (i < min) min = i;
                if (i > max) max = i;
            }
            this.sum = sum;
            this.average = (sum / renderTimes.size());
            this.min = min;
            this.max = max;
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

    private static Location[] flatten(BidimensionalLocation[][] locations){
        Location[] ret = new Location[locations.length * locations[0].length];

        int i = 0;
        for (Location[] row : locations)
            for (Location l : row)
                ret[i++] = l;

        return ret;
    }

    private static String toString(Generation gen, BidimensionalLocation[][] locations) {
        String s = "\n\n\n\n";

        for (BidimensionalLocation[] rows : locations) {
            s += " ";
            for (BidimensionalLocation location : rows) {
                final Cell cell = gen.lookAt(location);
                s += cell instanceof AliveCell ? 'O' : ' ';
            }
            s += "\n";
        }

        return s;
    }
}
