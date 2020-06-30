package tictactoe;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //initializes game board
        boolean inGame = true;
        char[][] board = new char[3][3];
        for (char[] row: board) Arrays.fill(row, ' ');

        display(board);

        while (inGame) {
            playerMove(board, 1);
            display(board);
            inGame = gameStatus(board);
            if (!inGame) break;
            playerMove(board, 2);
            display(board);
            inGame = gameStatus(board);
        }
    }

    //Method to display board while in game
    private static void display(char[][] arr) {
        System.out.println("---------");
        System.out.println("| " + arr[0][0] + ' ' + arr[0][1] + ' ' + arr[0][2] + " |");
        System.out.println("| " + arr[1][0] + ' ' + arr[1][1] + ' ' + arr[1][2] + " |");
        System.out.println("| " + arr[2][0] + ' ' + arr[2][1] + ' ' + arr[2][2] + " |");
        System.out.println("---------");

    }

    //Method to validate & input player move
    private static void playerMove(char[][] board, int player) {
        Scanner sc = new Scanner(System.in);
        int xCoord, yCoord;
        boolean validMove = false;

        while (!validMove) {
            System.out.print("Enter the coordinates: ");

            //Validates int inputs
            if (sc.hasNextInt()) {
                xCoord = sc.nextInt();
            } else {
                sc.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }
            if (sc.hasNextInt()) {
                yCoord = sc.nextInt();
            } else {
                sc.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }

            //Validates inputs are in range
            int rowIndex = 3 - yCoord;
            int colIndex = xCoord - 1;
            if (rowIndex < 0 || rowIndex > 2 || colIndex < 0 || colIndex > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (board[rowIndex][colIndex] == 'X' || board[rowIndex][colIndex] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            //Determine player's character & writes to board
            validMove = true;

            char symbol = 'X';
            switch (player) {
                case 1:
                    symbol = 'X';
                    break;
                case 2:
                    symbol = 'O';
                    break;
            }
            board[rowIndex][colIndex] = symbol;
        }
    }

    //Method to check status of game
    private static boolean gameStatus(char[][] board) {
        boolean xWins = false;
        boolean oWins = false;
        boolean impossible = false;
        boolean draw = false;
        boolean inGame = true;

        //check win condition
        if (threeRow(board, 'X')) {
            xWins = true;
            System.out.println("X wins");
        }
        if (threeRow(board, 'O')) {
            oWins = true;
            System.out.println("O wins");
        }

        //check Impossible condition
        if ((Math.abs(countChar(board, 'X') - countChar(board, 'O')) >= 2) ||
                threeRow(board, 'X') && threeRow(board, 'O')) {
            impossible = true;
            System.out.println("This game should be impossible...how did you do it!?");
        }

        //check Draw condition
        if (((countChar(board, 'X') == 4 && countChar(board, 'O') == 5) ||
                (countChar(board, 'X') == 5 && countChar(board, 'O') == 4)) &&
                (!threeRow(board, 'X') && !threeRow(board, 'O'))) {
            draw = true;
            System.out.println("Draw");
        }

        //ends game if any of above conditions are satisfied
        if (xWins || oWins || draw || impossible) inGame = false;
        return inGame;
    }

    //character count method to read char input
    private static int countChar(char[][] board, char c) {
        int count = 0;

        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == c) count++;
            }
        }
        return count;
    }

    //method to check win condition for specified player
    private static boolean threeRow(char[][] board, char c) {
        boolean win = false;

        //column
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == c) win = true;
            }
        }

        //row
        for (char[] chars : board) {
            if (chars[0] == chars[1] && chars[1] == chars[2]) {
                if (chars[0] == c) win = true;
            }
        }

        //diagonal
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[2][0] == board[1][1] && board[1][1] == board[0][2])) {
            if (board [1][1] == c) win = true;
        }

        return win;
    }
}