package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RealPlayer implements Player {
    @Override
    public void move(Field field) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            //Collects & validates coordinate inputs
            System.out.println(field);

            System.out.print("Enter the coordinates: ");
            try {
                int x = sc.nextInt();
                int y = sc.nextInt();

                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field.isEmpty(x, y)) {
                    field.set(x, y);
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
