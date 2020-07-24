package readability;

public class ScoreContext {
    ScoreStrategy strategy;

    public ScoreContext(ScoreStrategy strategy) {
        this.strategy = strategy;
    }

    public double getScore(String text) {
        return strategy.calculate(text);
    }
}
