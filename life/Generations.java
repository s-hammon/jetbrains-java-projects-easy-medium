package life;

public class Generations {

    public static void nextGen(Universe universe) {
        boolean[][] current = universe.getUniverse();
        int n = current.length;
        boolean[][] next = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; i++) {
                int count = countNeighbors(current, i, j);

                if (!current[i][j] && count == 3) next[i][j] = true;
                else if (current[i][j] && (count == 2 || count == 3)) next[i][j] = true;
                else next[i][j] = false;
            }
        }

        universe.setUniverse(next);
    }

    private static int countNeighbors(boolean[][] universe, int row, int col) {
        int count = 0;
        int size = universe.length;

        for(int i = row - 1; i <= row + 1; i++) {
            for (int j = col -1; j <= col + 1; j++) {

                //skips middle cell (the cell whose neighbors we are finding)
                if(i==row && j==col){
                    continue;
                }

                //handles cells at edges of array
                if (universe[(i + size) % size][(j + size) % size]) {
                    count++;
                }
            }
        }
        return count;

    }
}