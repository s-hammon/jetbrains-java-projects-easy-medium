package life;

import life.GameOfLife;

public class UpdateThread extends Thread {

    private Universe universe;
    private GameOfLife view;
    private int gen;

    public UpdateThread(Universe universe, GameOfLife view, int gen) {
        this.universe = universe;
        this.view = view;
        this.gen = gen;
    }

    @Override
    public void run() {
        view.getGenerationLabel().setText("Generation #" + gen);
        view.getGenerationLabel().repaint();

        view.getAliveLabel().setText("Alive: " + universe.countAlive());
        view.getAliveLabel().repaint();

        view.getUniversePanel().updateCellArray(universe.getUniverse());
    }
}
