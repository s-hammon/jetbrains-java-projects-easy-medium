package readability;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String ARI = "ari";
    private static final String FK = "fk";
    private static final String SMOG = "smog";
    private static final String CL = "cl";
    private static final String ALL = "all";

    public static void main(String[] args) {

        if (args[0] != null) {
            String text = getText(args[0]);
            printStatistics(text);

            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            final Scanner sc = new Scanner(System.in);

            switch (sc.next().toLowerCase()) {
                case ARI:
                    ari(text);
                    break;
                case FK:
                    fk(text);
                    break;
                case SMOG:
                    smog(text);
                    break;
                case CL:
                    cl(text);
                    break;
                case ALL:
                    all(text);
                    break;
                default:
                    System.out.println("Error: input unkown. Exiting program...");
            }
        }
    }

    private static void printStatistics(String text) {
        TextReader input = new TextReader(text);

        System.out.println("The text is:\n" + text);
        System.out.println();
        System.out.println("Words: " + input.getWords());
        System.out.println("Sentences: " + input.getSentences());
        System.out.println("Characters: " + input.getCharacters());
        System.out.println("Syllables: " + input.getSyllables());
        System.out.println("Polysyllables: " + input.getPolySyllables());    }

    private static void all(String text) {
        double score = getScore(text, new AutomatedScore());
        double totalAge = getAge(score);
        System.out.format("%nAutomated Readability Index: %.2f (about %d year olds).", score, getAge(score));

        score = getScore(text, new FleschKincaidScore());
        totalAge += getAge(score);
        System.out.format("%nFlesch–Kincaid readability tests: %.2f (about %d year olds).", score, getAge(score));

        score = getScore(text, new SmogScore());
        totalAge += getAge(score);
        System.out.format("%nSimple Measure of Gobbledygook: %.2f (about %d year olds).", score, getAge(score));

        score = getScore(text, new ColemanLiauScore());
        totalAge += getAge(score);
        System.out.format("%nColeman–Liau index: %.2f (about %d year olds).", score, getAge(score));

        System.out.format("%n%nThis text should be understood in average by %.2f year olds.", totalAge / 4);
    }

    private static void cl(String text) {
        double score = getScore(text, new ColemanLiauScore());
        System.out.format("%nColeman-Liau index: %.2f (about %d year olds).",
                score, getAge(score));
    }

    private static void smog(String text) {
        double score = getScore(text, new SmogScore());
        System.out.format("%nSimple Measure of Gobbledygook: %.2f (about %d year olds).",
                score, getAge(score));
    }

    private static void fk(String text) {
        double score = getScore(text, new FleschKincaidScore());
        System.out.format("%nFlesch-Kincaid readability tests: %.2f (about %d  year olds).",
                score, getAge(score));
    }

    private static void ari(String text) {
        double score = getScore(text, new AutomatedScore());
        System.out.format("%nAutomated Readability Index: %.2f (about %d year olds).",
                score, getAge(score));
    }

    private static double getScore(String text, ScoreStrategy strategy) {
        ScoreContext context = new ScoreContext(strategy);
        return context.getScore(text);
    }

    private static String getText(String fileName) {
        StringBuilder text = new StringBuilder();
        File file = new File(fileName);

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine())
                text.append(sc.nextLine());
        } catch (IOException e) {
            System.out.println("File not found.");
        }

        return text.toString();
    }

    private static int getAge(double score) {
        int[] levels = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24};
        int level = (int) Math.round(score);

        level = level > 1 ? 1 : Math.min(level, 13);
        return levels[level -1];
    }
}
