package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int command;

        ProcessorMenu menu = new ProcessorMenu();

        do {
            command = menu.mainMenu();
            switch (command) {
                case 1:
                    menu.addMatrices();
                    break;
                case 2:
                    menu.scalarMultiply();
                    break;
                case 3:
                    menu.dotProduct();
                    break;
                case 4:
                    menu.transposeMatrix();
                    break;
                case 5:
                    menu.calculateDeterminant();
                    break;
                case 6:
                    menu.invertMatrix();
                    break;
                default:
                    break;
            }
        } while (command != 0);
    }
}