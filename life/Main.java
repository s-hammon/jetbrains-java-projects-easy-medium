package life;

public class Main {

    public static void main(String[] args) {
        int n = 20;

        Universe universe = new Universe(n);
        GameOfLife view = new GameOfLife();
        Controller controller = new Controller(universe, view, n);
        controller.start();
    }
}