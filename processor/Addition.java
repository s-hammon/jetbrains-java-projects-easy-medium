package processor;

class Addition implements Operation {

    @Override
    public double[][] operate(double[][] a, double[][] b) {
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] += b[i][j];
            }
        }

        return a;
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