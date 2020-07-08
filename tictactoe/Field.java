package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Field {
    private final Symbol[][] field;
    private boolean xNext;
    private Symbol winner;

    public Field() {
        //initializes tic-tac-toe board
        xNext = true;
        field = new Symbol[][] {
                {Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY},
                {Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY},
                {Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY}
        };
        winner = null;

    }

    public boolean isEmpty(Score score) {
        return isEmpty(score.getX(), score.getY());
    }

    public boolean isEmpty(int x, int y) {
        //checks if a space is empty
        int col = x - 1;
        int row = 3 - y;

        return field[row][col] == Symbol.EMPTY;
    }

    public void set(Score score) {
        set(score.getX(), score.getY());
    }

    void set(int x, int y) {
        //places move & checks win condition
        if (!isEmpty(x, y) || winner != null) throw new IllegalStateException();


        int col = x - 1;
        int row = 3 - y;

        field[row][col] = nextSymbol();
        xNext = !xNext;

        winner = checkWinner();
    }

    void remove(int x, int y) {
        //erases move & checks win condition (for hardComp)
        if (isEmpty(x, y)) throw new IllegalStateException();

        int col = x - 1;
        int row = 3 - y;

        field[row][col] = Symbol.EMPTY;
        xNext = !xNext;

        winner = checkWinner();
    }

    private Symbol checkWinner() {
        //checks win condition
        if (checkRows(Symbol.X) || checkColumns(Symbol.X) || checkDiagonals(Symbol.X)) {
            return Symbol.X;
        } else if (checkRows(Symbol.O) || checkColumns(Symbol.O) || checkDiagonals(Symbol.O)) {
            return Symbol.O;
        } else if (isFilled()) {
            return Symbol.EMPTY;
        }

        return null;
    }

    boolean willWin(int x, int y, Symbol symbol) {
        //Sees if playing a spot is a winning move
        int col = x - 1;
        int row = 3 - y;

        if (isEmpty(x, y)) {
            Symbol before = field[row][col];
            field[row][col] = symbol;

            boolean won = checkWinner() == symbol;

            field[row][col] = before;
            return won;
        }

        return false;
    }

    Symbol nextSymbol() {
        //sets symbol based on who is next
        return xNext ? Symbol.X : Symbol.O;
    }

    //next 3 methods check if there is a 3 in a row
    private boolean checkRows(Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == symbol && field[i][1] == symbol && field[i][2] == symbol) {
                return true;
            }
        }

        return false;
    }

    private boolean checkColumns(Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == symbol && field[1][i] == symbol && field[2][i] ==symbol) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonals(Symbol symbol) {
        return (field[1][1] == symbol) &&
                (((field[0][0] == symbol) && (field[2][2] == symbol)) ||
                        ((field[0][2] == symbol) && (field[2][0] == symbol)));
    }

    private boolean isFilled() {
        //checks if a space is empty on the board
        return Arrays.stream(field)
                .flatMap(Arrays::stream)
                .noneMatch(symbol -> symbol == Symbol.EMPTY);
    }

    public boolean inGame() {
        //if a winner is yet to be declared, the game is still going
        return winner == null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------")
                .append(System.lineSeparator());

        for (Symbol[] row : field) {
            String line = Arrays.stream(row).map(Symbol::getSymbol).collect(Collectors.joining(" "));

            stringBuilder.append("| ")
                    .append(line)
                    .append((" |"))
                    .append(System.lineSeparator());
        }

        stringBuilder.append("---------");
        return stringBuilder.toString();
    }

    public Symbol getWinner() {
        return winner;
    }

}
