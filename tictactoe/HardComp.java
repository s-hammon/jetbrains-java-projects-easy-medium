package tictactoe;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HardComp implements Player {
    //programming for hard difficulty
    final List<Score> iterOrder;

    public HardComp() {
        //Assigns score value to each space
        this.iterOrder = List.of(
                new Score(2, 2),

                new Score(1, 3),
                new Score(3, 1),
                new Score(1, 1),
                new Score(3, 3),

                new Score(2, 3),
                new Score(3, 2),
                new Score(2, 1),
                new Score(1, 2)
        );
    }

    @Override
    public void move(Field field) {
        //Finds the best move and places symbol
        System.out.println(field);
        System.out.println("Making move level \"hard\"");

        Symbol symbol = field.nextSymbol();

        Score next = getBestMove(field, symbol);

        field.set(next);
    }

    private Score getBestMove(Field field, Symbol symbol) {
        //Filters out empty spaces and compares scores
        Stream<Score> stream = iterOrder.stream().filter(field::isEmpty);
        Comparator<Score> comparator = Comparator.comparing(score -> getScore(field, score, symbol));
        //Gets the max/min score if it's the computer/player turn
        return (symbol == Symbol.X ? stream.max(comparator) : stream.min(comparator)).orElseThrow();
    }

    private int getScore(Field field, Score score, Symbol symbol) {
        //grabs score from method below
        return getScore(field, score.getX(), score.getY(), symbol);
    }

    private int getScore(Field field, int x, int y, Symbol symbol) {
        field.set(x, y);

        if (!field.inGame()) {
            Symbol winner = field.getWinner();
            field.remove(x, y);

            if (winner == Symbol.EMPTY) return 0;
            return winner == Symbol.X ? 1 : -1;
        } else {
            Symbol opposite = symbol.opposite();

            IntStream stream = iterOrder.stream()
                    .filter(field::isEmpty)
                    .mapToInt(score -> getScore(field, score, opposite));
            int score = (opposite == Symbol.X ? stream.max() : stream.min()).orElseThrow();

            field.remove(x, y);
            return score;
        }
    }
}
