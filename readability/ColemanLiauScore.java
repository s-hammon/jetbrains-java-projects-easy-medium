package readability;

public class ColemanLiauScore implements ScoreStrategy {

    @Override
    public double calculate(String text) {
        final TextReader input = new TextReader(text);
        final double chars = input.getCharacters();
        final double words = input.getWords();
        final double sentences = input.getSentences();

        final double L = chars / words * 100.0;
        final double S = 100 / (words / sentences);

        return 0.0588 * L - 0.296 * S - 15.8;
    }
}
