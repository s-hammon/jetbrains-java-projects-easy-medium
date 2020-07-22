package processor;

public class InvertMatrix implements Operation {

    @Override
    public double[][] operate(double[][] matrix, double c) {
        double[][] inverted = new double[matrix.length][matrix.length];
        double[][] b = new double[matrix.length][matrix.length];
        int[] index = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            b[i][i] = 1;
        }

        gaussian(matrix, index);
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    b[index[j]][k] -= matrix[index[j]][i] * b[index[i]][k];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            inverted[matrix.length - 1][i] = b[index[matrix.length - 1]][i]
                    / matrix[index[matrix.length - 1]][matrix.length - 1];

            for (int j = matrix.length - 2; j >= 0; j--) {
                inverted[j][i] = b[index[j]][i];
                for (int k = j + 1; k < matrix.length; k++) {
                    inverted[j][i] -= matrix[index[j]][k] * inverted[k][i];
                }
                inverted[j][i] /= matrix[index[j]][j];
            }
        }

        return inverted;
    }

    public void gaussian(double[][] matrix, int[] index) {
        double[] a = new double[index.length];

        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        for (int i = 0; i < index.length; i++) {
            double a1 = 0;
            for (int j = 0; j < index.length; j++) {
                double a0 = Math.abs(matrix[i][j]);

                if (a0 > a1) a1 = a0;
            }

            a[i] = a1;
        }

        int k = 0;
        for (int j = 0; j < index.length - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < index.length; ++i) {
                double pi0 = Math.abs(matrix[index[i]][j]);
                pi0 /= a[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < index.length; i++) {
                double pj = matrix[index[i]][j] / matrix[index[j]][j];
                matrix[index[i]][j] = pj;
                for (int l = j + 1; l < index.length; l++) {
                    matrix[index[i]][l] -= pj * matrix[index[j]][l];
                }
            }
        }
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
