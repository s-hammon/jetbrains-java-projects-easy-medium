package readability;

public class TextReader {

    private final String text;

    public TextReader(String text) {
        this.text = text.toLowerCase();
    }

    public int getWords() {
        return text.split("\\s+").length;
    }

    public int getSentences() {
        String text = this.text.trim();
        return text.split("[?.!]").length;
    }

    public int getCharacters() {
        return text.replaceAll("\\s+", "").length();
    }

    public int getSyllables() {
        int syllables = 0;
        String[] words = text.split("\\s+|\\.\\s+|\\?\\s+|!\\s+");

        for (String w : words) {
            int count = getWordSyllables(w);
            count = count > 0 ? count : 1;
            syllables += count;
        }

        return syllables;
    }

    private int getWordSyllables(String word) {
        String vowels = "aeiouy";
        int syllables = 0;
        boolean flag = false;

        for (int i = 0; i < word.length(); i++) {
            char thisChar = word.charAt(i);

            if (i == word.length() - 1 && word.charAt(i) == 'e') {
                break;
            }

            if (vowels.indexOf(thisChar) >= 0) {
                if (!flag) {
                    syllables++;
                }

                flag = true;
            } else {
                flag = false;
            }
        }

        return syllables;
    }

    public int getPolySyllables() {
        int polys = 0;
        String[] words = text.split("\\s+|\\.\\s+|\\?\\s+|!\\s+");

        for (String w : words) {
            int count = getWordSyllables(w);
            count = count > 2 ? 1 : 0;
            polys += count;
        }

        return polys;
    }
}
