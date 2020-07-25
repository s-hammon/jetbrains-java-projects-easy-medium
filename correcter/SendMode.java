package correcter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class SendMode implements Mode {

    @Override
    public void process() {
        try (FileInputStream input = new FileInputStream(encoded);
            FileOutputStream output = new FileOutputStream(received)) {

            byte[] bytes = input.readAllBytes();
            byte[] error = toError(bytes);
            output.write(error);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private byte[] toError(byte[] parity) {
        byte[] error = new byte[parity.length];

        Random rand = new Random();
        for (int i = 0; i < parity.length; i++) {
            int pos = rand.nextInt(7);
            error[i] = (byte) (parity[i] ^ 1 << pos);
        }

        return error;
    }
}
