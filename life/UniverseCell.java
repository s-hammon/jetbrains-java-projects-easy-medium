package life;

import javax.swing.*;
import java.awt.*;

public class UniverseCell extends JPanel {

    private boolean alive;

    public UniverseCell() {
        super();
        setVisible(false);
        setBackground(Color.BLACK);
    }

    public UniverseCell(boolean alive) {
        super();
        setAlive(alive);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        setVisible(this.alive);
    }

    public boolean isAlive() {return alive;}
}
