package encryptdecrypt;

class unicodeAlgorithm implements methodAlgorithm {
    /**
     * Sign of variable 'key' determines whether this will encrypt or
     * decrypt. Positive = encrypt, negative = decrypt
     */
    @Override
    public String runAlgorithm(String mode, int key, String data) {
        char[] arr = new char[data.length()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char)(data.charAt(i) + key);
        }

        return new String(arr);
    }
}
