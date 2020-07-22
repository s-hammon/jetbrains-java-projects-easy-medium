package processor;

class DotProduct implements Operation {

    @Override
    public double[][] operate(double[][] a, double[][] b) {

        double[][] dotProduct = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    dotProduct[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return dotProduct;
    }

    @Override
    public double[][] operate(double[][] matrix, double c) {
        return null;
    }

    @Override
    public double operate(double[][] matrix) {
        return 0;
    }
}