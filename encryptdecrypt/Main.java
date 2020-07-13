package encryptdecrypt;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        //initializing variables including 'default' values
        String mode = "enc";
        String algorithm = "shift";
        String in = null;
        String out = null;
        int key = 0;
        String data = "";
        //Reads command-line inputs, finds arguments & defines variables
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[++i];
                    break;
                case "-alg":
                    algorithm = args[++i];
                    break;
                case "-key":
                    key = Integer.parseInt(args[++i]);
                    break;
                case "-data":
                    data = args[++i];
                    break;
                case "-in":
                    in = args[++i];
                    break;
                case "-out":
                    out = args[++i];
                    break;
            }
        }
        //Sets key value to negative if decrypting
        // (see algorithm class notes)
        if ("dec".equals(mode)) key = -key;
        //Sets data value to read file if applicable
        if (data.equals("") && in != null) {
            data = Files.readString(Path.of(in));
        }
        //Implements algorithms based on input command
        Algorithm alg = null;

        switch (algorithm) {
            case "unicode":
                alg = new Algorithm(new unicodeAlgorithm());
                break;
            case "shift":
                alg = new Algorithm(new shiftAlgorithm());
                break;
        }

        if (alg == null) {
            throw new RuntimeException(
                    "Unknown algorithm type passed. Contact author."
            );
        }

        //Prints either to standard or export file
        PrintStream printStream = out == null ? System.out : new PrintStream(out);
        printStream.print(alg.run(mode, key, data));
    }
}