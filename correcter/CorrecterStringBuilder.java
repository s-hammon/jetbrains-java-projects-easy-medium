package correcter;

import java.util.StringJoiner;
import java.util.function.Function;

public class CorrecterStringBuilder {
    private final StringJoiner joiner;

    private StringBuilder builder;

    CorrecterStringBuilder() {
        this("");
    }

    CorrecterStringBuilder(String delimiter) {
        this.joiner = new StringJoiner(delimiter);
        this.builder = new StringBuilder();
    }

    CorrecterStringBuilder append(String text) {
        builder.append(text);
        return this;
    }

    CorrecterStringBuilder append(char ch) {
        builder.append(ch);
        return this;
    }

    void delimit() {
        String text = builder.toString();
        if (!text.isEmpty()) {
            joiner.add(text);
            builder = new StringBuilder();
        }
    }

    void delimit(Function<String, String> mapper) {
        String text = builder.toString();
        if (!text.isEmpty()) {
            joiner.add(mapper.apply(text));
            builder = new StringBuilder();
        }
    }

    @Override
    public String toString() {
        return this.joiner.toString();
    }}
