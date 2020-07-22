package processor;

public class TransposeMatrix implements Operation {

    @Override
    public double[][] operate(double[][] matrix, double c) {
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] transposed = new double[row][col];

        switch ((int) c) {
            case 1:
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        transposed[i][j] = matrix[j][i];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        transposed[i][j] = matrix[row - 1 - j][col - 1 - i];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        transposed[i][j] = matrix[i][col - 1 - j];
                    }
                }
                break;
            case 4:
                for (int i = 0; i < row; i++) {
                    for(int j = 0; j < col; j++) {
                        transposed[i][j] = matrix[row - 1 - i][j];
                    }
                }
                break;
            default:
                break;
        }

        return transposed;
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