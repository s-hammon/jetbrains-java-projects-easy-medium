package correcter;

public interface Mode {
    String encoded = "encoded.txt";
    String received = "received.txt";
    String decoded = "decoded.txt";
    String send = "send.txt";

    void process();

    default int getBit(String binary, int pos) {
        return binary.charAt(pos) == '0' ? 0 : 1;
    }
}
