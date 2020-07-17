package life;

import java.io.IOException;

public class Controller extends Thread {

    private Universe universe;
    private GameOfLife view;
    private int size;

    public Controller(Universe universe, GameOfLife view, int size) {
        this.universe = universe;
        this.view = view;
        this.size = size;
    }

    public void run() {
        int gen = 0;

        while (true) {
            switch (view.state) {
                case RUNNING:
                    var t = new UpdateThread(universe, view, size);
                    t.start();

                    try {
                        t.join();
                    } catch (InterruptedException ex) {
                        System.out.println("error");
                    }

                    Generations.nextGen(universe);
                    gen++;

                    try {
                        sleep(500L);
                    } catch (InterruptedException ex) {
                        System.out.println("error");
                    }
                    break;
                case PAUSED:
                    break;
                case RESET:
                    view.state = life.State.RUNNING;
                    universe = new Universe(20);
                    break;
            }
        }
    }
}
