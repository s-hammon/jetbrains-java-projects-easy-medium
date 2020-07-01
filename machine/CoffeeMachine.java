package machine;

import java.util.Scanner;

public class CoffeeMachine {
    /*  defines constants for resource consumption, coffee prices,
     *  & initial machine resources
     */
    private static final int ESPRESSO_WATER = 250;
    private static final int ESPRESSO_MILK = 0;
    private static final int ESPRESSO_COFFEE_BEANS = 16;
    private static final int ESPRESSO_PRICE = 4;

    private static final int LATTE_WATER = 350;
    private static final int LATTE_MILK = 75;
    private static final int LATTE_COFFEE_BEANS = 20;
    private static final int LATTE_PRICE = 7;

    private static final int CAPP_WATER = 200;
    private static final int CAPP_MILK = 100;
    private static final int CAPP_COFFEE_BEANS = 12;
    private static final int CAPPUCCINO_PRICE = 6;

    private static final Scanner scanner = new Scanner(System.in);

    private static int money = 550;
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int cups = 9;

    public static void main(String[] args) {
        System.out.print("Write action (buy, fill, take, remaining, exit):\n> ");

        while (true) {
            switch (scanner.next()) {
                case "remaining":
                    displayResources();
                    break;
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unexpected action.");
            }
        }
    }

    private static void displayResources() {
        //displays current machine resources
        System.out.printf("The coffee machine has:\n%d of water\n%d of milk\n", water, milk);
        System.out.printf("%d of coffee beans\n%d of disposable cups\n%d of money\n", coffeeBeans, cups, money);
    }

    private static void buyCoffee() {
        //manages customer order selection
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:\n> ");

        switch (scanner.next()) {
            case "back":
                return;
            case "1":
                makeCoffee(ESPRESSO_WATER, ESPRESSO_MILK, ESPRESSO_COFFEE_BEANS);
                processPayment(ESPRESSO_PRICE);
                break;
            case "2":
                makeCoffee(LATTE_WATER, LATTE_MILK, LATTE_COFFEE_BEANS);
                processPayment(LATTE_PRICE);
                break;
            case "3":
                makeCoffee(CAPP_WATER, CAPP_MILK, CAPP_COFFEE_BEANS);
                processPayment(CAPPUCCINO_PRICE);
                break;
            default:
                System.out.println("Input error: please make the selection below, or type 'back' to return.");
                buyCoffee();
        }
    }

    private static void makeCoffee(int water, int milk, int coffeeBeans) {
        //makes coffee provided adequate resources
        if (CoffeeMachine.water < water) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (CoffeeMachine.milk < milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (CoffeeMachine.coffeeBeans < coffeeBeans) {
            System.out.println("Sorry, not enough coffee bean!");
            return;
        }

        if (CoffeeMachine.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        CoffeeMachine.water -= water;
        CoffeeMachine.milk -= milk;
        CoffeeMachine.coffeeBeans -= coffeeBeans;
        CoffeeMachine.cups--;

        System.out.println("I have enough resources, making you a coffee!");
    }

    private static void processPayment(int price) {
        //accepts the money payment
        money += price;
    }

    private static void fillMachine() {
        //replenishes resources based on employee input (assumes correct input)
        System.out.print("Write how many ml of water do you want to add:\n> ");
        water += scanner.nextInt();

        System.out.print("Write how many ml of milk do you want to add:\n> ");
        milk += scanner.nextInt();

        System.out.print("Write how many grams of coffee beans do you want to add:\n> ");
        coffeeBeans += scanner.nextInt();

        System.out.print("Write how many disposable cups of coffee do you want to add:\n >");
        cups += scanner.nextInt();
    }

    private static void takeMoney() {
        //dispenses money to employee
        System.out.printf("I gave you $%d", money);
        money = 0;
    }
}
