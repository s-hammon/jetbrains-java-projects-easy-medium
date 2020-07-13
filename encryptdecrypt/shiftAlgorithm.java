package encryptdecrypt;

class shiftAlgorithm implements methodAlgorithm {
    /**
     * Sign of variable 'key' determines whether this will encrypt or
     * decrypt. Positive = encrypt, negative = decrypt
     */
    @Override
    public String runAlgorithm(String mode, int key, String data) {
        char[] arr = new char[data.length()];

        for (int i = 0; i < arr.length; i++) {
            if (data.charAt(i) >= 97 && data.charAt(i) <= 122) {
                if (key >= 0) {
                    int val = (int)data.charAt(i) + key;
                    val = val > 122 ? 96 + val - 122 : val;
                    arr[i] = (char)val;
                } else {
                    int val = (int)data.charAt(i) + key;
                    val = val < 97 ? val + 26 : val;
                    arr[i] = (char)val;
                }
            } else arr[i] = data.charAt(i);
        }

        return new String(arr);
    }
}
