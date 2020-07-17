package life;

import java.util.Random;

public class Universe {

    private boolean[][] universe;
    private Random rand;

    public Universe(int n, long s) {
        universe = new boolean[n][n];
        rand = new Random(s);
        createUniverse();
    }

    public Universe(int n) {
        universe = new boolean[n][n];
        rand = new Random();
        createUniverse();
    }

    private void createUniverse() {
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[i].length; j++) {
                universe[i][j] = rand.nextBoolean();
            }
        }
    }

    public boolean[][] getUniverse() {
        return universe;
    }

    public void setUniverse(boolean[][] universe) {
        this.universe = universe;
    }

    public int countAlive() {
        int count = 0;

        for (boolean[] row : universe) {
            for (boolean cell : row) {
                if (cell) {
                    count++;
                }
            }
        }

        return count;
    }


}