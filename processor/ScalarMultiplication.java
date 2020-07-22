package processor;

class ScalarMultiplication implements Operation {

    @Override
    public double[][] operate(double[][] matrix,double c) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= c;
            }
        }

        return matrix;
    }

    @Override
    public double[][] operate(double[][] a, double[][] b) {
        return null;
    }

    @Override
    public double operate(double[][] matrix) {
        return 0;
    }
}