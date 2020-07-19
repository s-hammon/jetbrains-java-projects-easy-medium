package converter;

import java.util.Scanner;
import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        String base = "";
        String num = "";
        String newBase = "";

        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            base = sc.nextLine();
            if (testBase(base)) errorExit();
        } else errorExit();

        if (sc.hasNextLine()) {
          num = sc.nextLine();
          if (testNum(num))  errorExit();
        } else errorExit();

        if (sc.hasNextLine()) {
            newBase = sc.nextLine();
            if (testBase(newBase)) errorExit();
        } else errorExit();

        String[] numString = num.split("\\.");
        int sourceBase = Integer.parseInt(base);
        int targetBase = Integer.parseInt(newBase);

        if (numString.length == 1 || sourceBase == 1) {
            System.out.println(convertInteger(numString[0], sourceBase, targetBase));
        } else {
            String integer = convertInteger(numString[0], sourceBase, targetBase);
            String fraction = convertFraction(numString[1], sourceBase, targetBase);
            System.out.printf("%s.%s", integer, fraction);
        }
    }

    private static String convertInteger(String integer, int sourceBase, int targetBase) {
        int num;

        if (sourceBase != 10) {
            if (sourceBase == 1) {
                num = integer.length();
            } else num = Integer.parseInt(integer, sourceBase);
        } else num = Integer.parseInt(integer);

        if (targetBase == 1) {
            return "1".repeat(Math.max(0, num));
        }

        return Integer.toString(num, targetBase);
    }

    private static String convertFraction(String fraction, int sourceBase, int targetBase) {
        double decimal = 0.0;

        if (sourceBase == 10) decimal = Double.parseDouble("0." + fraction);
        else {
            char c;
            for (int i = 0; i < fraction.length(); i++) {
                c = fraction.charAt(i);
                decimal += Character.digit(c, sourceBase) / Math.pow(sourceBase, i + 1);
            }
        }

        StringBuilder newFraction = new StringBuilder();
        int dec;

        for (int i = 0; i < 5; i++) {
            double aux = decimal * targetBase;
            dec = (int) aux;
            newFraction.append(Long.toString(dec, targetBase));
            decimal = aux - dec;
        }

        return newFraction.toString();
    }

    private static void errorExit() {
        System.out.println("error");
        exit(0);
    }

    private static boolean testNum(String num) {
        return num == null || num.length() == 0;
    }

    private static boolean testBase(String base) {
        if (base.matches("[0-9]+")) {
            int n = Integer.parseInt(base);
            return n <= 0 || n > Character.MAX_RADIX;
        }

        return true;
    }
}