package encryptdecrypt;

class Algorithm {
    //Strategy method with 'methodAlgorithm' interface
    private methodAlgorithm algorithm;

    public Algorithm(methodAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String run(String mode, int key, String data) {
        return algorithm.runAlgorithm(mode, key, data);
    }
}
