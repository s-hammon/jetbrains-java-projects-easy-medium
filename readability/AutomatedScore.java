package readability;

import org.w3c.dom.Text;

public class AutomatedScore implements ScoreStrategy {

    @Override
    public double calculate(String text) {
        final TextReader input = new TextReader(text);
        final double chars = input.getCharacters();
        final double words = input.getWords();
        final double sentences = input.getSentences();

        return 4.71 * chars / words + 0.5 * words / sentences - 21.43;
    }
}
