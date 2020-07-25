package correcter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DecodeMode implements Mode {

    @Override
    public void process() {
        try (FileInputStream input = new FileInputStream(received);
             FileWriter output = new FileWriter(decoded)) {

            byte[] bytes = input.readAllBytes();
            String message = toDecode(toBinary(fromBytes(bytes)));
            output.write(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String[] fromBytes(byte[] bytes) {
        StringBuilder error = new StringBuilder();

        for (byte b : bytes) {
            String str = Integer.toString(Byte.toUnsignedInt(b), 2);
            error.append("0".repeat(8 - str.length()))
                    .append(str)
                    .append(" ");
        }

        return error.toString().split(" ");
    }

    private String correctErrorBit(String binary) {
        boolean p1 = (getBit(binary, 0) ^ getBit(binary, 2) ^ getBit(binary, 4)) == getBit(binary, 6);
        boolean p2 = (getBit(binary, 1) ^ getBit(binary, 2) ^ getBit(binary, 5)) == getBit(binary, 6);
        boolean p4 = (getBit(binary, 3) ^ getBit(binary, 4) ^ getBit(binary, 5)) == getBit(binary, 6);

        StringBuilder corrected = new StringBuilder();
        if (!p1 && !p2 && p4) {
            return corrected.append(binary.charAt(2) == '0' ? '1' : '0')
                    .append(binary.charAt(4))
                    .append(binary.charAt(5))
                    .append(binary.charAt(6)).toString();
        }
        if (!p1 && p2 && !p4) {
            return corrected.append(binary.charAt(2))
                    .append(binary.charAt(4) == '0' ? '1' : '0')
                    .append(binary.charAt(5))
                    .append(binary.charAt(6)).toString();
        }
        if (p1 && !p2 && !p4) {
            return corrected.append(binary.charAt(2))
                    .append(binary.charAt(4))
                    .append(binary.charAt(5) == '0' ? '1' : '0')
                    .append(binary.charAt(6)).toString();
        }
        if (!p1 && !p2 && !p4) {
            return corrected.append(binary.charAt(2))
                    .append(binary.charAt(4))
                    .append(binary.charAt(5))
                    .append(binary.charAt(6) == '0' ? '1' : '0').toString();
        }

        return corrected.append(binary.charAt(2))
                .append(binary.charAt(4))
                .append(binary.charAt(5))
                .append(binary.charAt(6)).toString();
    }

    private String toBinary(String[] error) {
        StringBuilder binary = new StringBuilder();

        for (String s : error) {
            binary.append(correctErrorBit(s));
        }

        return binary.substring(0, binary.length() - binary.length() % 8);
    }

    private String toDecode(String binary) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < binary.length() / 8; i++) {
            message.append((char) Integer.parseInt(binary.substring(i * 8, (i + 1) * 8), 2));
        }

        return message.toString();
    }
}
