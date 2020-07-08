package tictactoe;

enum Symbol {
    //Defines symbols on the tic tac toe board
    X("X") {
        @Override
        public Symbol opposite() {
            return O;
        }
    },
    O("O") {
        @Override
        public Symbol opposite() {
            return X;
        }
    },
    EMPTY(" ") {
        @Override
        public Symbol opposite() {
            return EMPTY;
        }
    };

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract Symbol opposite();

}