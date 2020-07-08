package tictactoe;

public class MediumComp implements Player {

    @Override
    public void move(Field field) {
        System.out.println(field);
        System.out.println("Making move level \"medium\"");

        if (winMove(field)) return;
        if (trumpMove(field)) return;

        moveRandomly(field);
    }

    private boolean winMove(Field field) {
        Symbol symbol = field.nextSymbol();
        return checkNextTurnWin(field, symbol);
    }

    private boolean trumpMove(Field field) {
        Symbol symbol = field.nextSymbol().opposite();
        return checkNextTurnWin(field, symbol);
    }
}