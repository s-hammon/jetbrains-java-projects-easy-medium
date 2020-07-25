package correcter;

import java.util.Scanner;

public class ErrorCorrecting {
    private Mode setMode(String mode) {
        switch (mode) {
            case "encode":
                return new EncodeMode();
            case "decode":
                return new DecodeMode();
            case "send":
                return new SendMode();
            default:
                return null;
        }
    }

    public void command() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Write a mode: ");
        String mode = sc.nextLine();

        setMode(mode).process();
    }
}
