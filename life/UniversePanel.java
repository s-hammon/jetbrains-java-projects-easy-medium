package life;

import javax.swing.*;
import java.awt.*;

public class UniversePanel extends JPanel {
    private UniverseCell[][] cellArray;

    public UniversePanel() {
        super();
    }

    public void initialize(int n) {
        this.setLayout(new GridLayout(n, n, 1, 1));
        this.cellArray = new UniverseCell[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cellArray[i][j] = new UniverseCell();
                this.add(cellArray[i][j]);
            }
        }
    }

    public void updateCellArray(boolean[][] universe) {
        if (cellArray != null) {
            for (int i = 0; i < universe.length; i++) {
                for (int j = 0; j < universe.length; j++) {
                    if (cellArray[i][j].isAlive() != universe[i][j]) {
                        cellArray[i][j].setAlive(universe[i][j]);
                    }
                }
            }
            repaint();
        } else {
            initialize(universe.length);
            updateCellArray(universe);
        }
    }
}
