package readability;

public class FleschKincaidScore implements ScoreStrategy {

    @Override
    public double calculate(String text) {
        final TextReader input = new TextReader(text);
        final double syllables = input.getSyllables();
        final double words = input.getWords();
        final double sentences = input.getSentences();

        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }
}
