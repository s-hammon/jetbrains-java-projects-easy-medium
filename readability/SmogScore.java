package readability;

import org.w3c.dom.Text;

public class SmogScore implements ScoreStrategy {

    @Override
    public double calculate(String text) {
        final TextReader input = new TextReader(text);
        final double polys = input.getPolySyllables();
        final double sentences = input.getSentences();

        return 1.043 * Math.sqrt((polys * 30) / sentences) + 3.1291;
    }
}
