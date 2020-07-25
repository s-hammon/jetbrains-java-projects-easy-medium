package correcter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeMode implements Mode {

    @Override
    public void process() {
        try (FileInputStream input = new FileInputStream(send);
            FileOutputStream output = new FileOutputStream(encoded)) {

            byte[] bytes = input.readAllBytes();
            byte[] parity = toParity(toExpanded(getMessage(bytes)));

            output.write(parity);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getMessage(byte[] message) {
        StringBuilder binary = new StringBuilder();

        for (byte b : message) {
            String str = Integer.toString(b, 2);
            binary.append("0".repeat(8 - str.length()))
                    .append(str);
        }

        return binary.toString();
    }

    private String[] toExpanded(String binary) {

        StringBuilder expanded = new StringBuilder();
        for (int i = 0, l = binary.length() / 4; i < l; i++) {
            String bits= binary.substring(i * 4, (i + 1) * 4);
            expanded.append('.')
                    .append('.')
                    .append(bits.charAt(0))
                    .append('.')
                    .append(bits.charAt(1))
                    .append(bits.charAt(2))
                    .append(bits.charAt(3))
                    .append('.');
        }

        CorrecterStringBuilder builder = new CorrecterStringBuilder();
        for (int i = 0, l = expanded.toString().length() / Byte.SIZE; i < l; i++) {
            String bits = expanded.toString().substring(i * Byte.SIZE, (i + 1) * Byte.SIZE);
            int countOnes = 0;
            if (bits.charAt(2) == '1') ++countOnes;
            if (bits.charAt(4) == '1') ++countOnes;
            if (bits.charAt(6) == '1') ++countOnes;
            if (countOnes % 2 == 0) {
                builder.append('0');
            } else {
                builder.append('1');
            }
            countOnes = 0;
            if (bits.charAt(2) == '1') ++countOnes;
            if (bits.charAt(5) == '1') ++countOnes;
            if (bits.charAt(6) == '1') ++countOnes;
            if (countOnes % 2 == 0) {
                builder.append('0');
            } else {
                builder.append('1');
            }
            builder.append(bits.charAt(2));
            countOnes = 0;
            if (bits.charAt(4) == '1') ++countOnes;
            if (bits.charAt(5) == '1') ++countOnes;
            if (bits.charAt(6) == '1') ++countOnes;
            if (countOnes % 2 == 0) {
                builder.append('0');
            } else {
                builder.append('1');
            }
            builder.append(bits.charAt(4))
                    .append(bits.charAt(5))
                    .append(bits.charAt(6))
                    .append('0')
                    .delimit();
        }

        String[] expandedMessage = new String[builder.toString().length() / Byte.SIZE];
        for (int i = 0; i < expandedMessage.length; i++){
            expandedMessage[i] = builder.toString().substring(i * 8, i * 8 + 8);
        }

        return expandedMessage;
    }

    private byte[] toParity(String[] expanded) {
        byte[] parity = new byte[expanded.length];

        for (int i = 0; i < parity.length; i++) {
            parity[i] = (byte) Integer.parseInt(expanded[i], 2);
        }

        return parity;
    }
}
