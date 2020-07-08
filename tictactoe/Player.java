package tictactoe;

import java.util.Random;

interface Player {
    static Player of(String type) {
        //Returns the human or computer class & methods
        switch (type) {
            case "easy":
                return new EasyComp();
            case "medium":
                return new MediumComp();
            case "hard":
                return new HardComp();
            case "user":
                return new RealPlayer();
            default:
                return null;
        }    }

    void move(Field field);

    default void moveRandomly(Field field) {
        //randomly selects an open spot on board
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(3) + 1;
            y = random.nextInt(3) + 1;
        } while (!field.isEmpty(x, y));

        field.set(x, y);
    }

    default boolean checkNextTurnWin(Field field, Symbol symbol) {
        //looks and plays potential winning/losing spot on board
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (field.willWin(i, j, symbol)) {
                    field.set(i, j );
                    return true;
                }
            }
        }

        return false;
    }
}
