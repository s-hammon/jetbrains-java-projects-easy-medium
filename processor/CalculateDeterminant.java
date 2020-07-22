package processor;

public class CalculateDeterminant implements Operation {

    @Override
    public double operate(double[][] matrix) {
        if (matrix.length == 2) {return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];}
        else {
            double det = 0.0;
            for (int j = 0; j<matrix.length; j++){
                if(j%2 == 0) {det += matrix[0][j]* operate(matrixMinor(matrix,0,j));}
                else {det -= matrix[0][j]* operate(matrixMinor(matrix,0,j));}
            }
            return det;
        }
    }

    public double[][] matrixMinor(double[][] matrix, int row, int col) {
        double [][] minor = new double[matrix.length-1][matrix.length-1];
        int itemp = 0;
        int jtemp = 0;

        for (int i = 0; i <matrix.length; i++){
            for (int j = 0; j <matrix.length; j++){
                if (i != row && j != col) {
                    minor[itemp][jtemp] = matrix[i][j];
                    jtemp++;}
            }

            jtemp=0;
            if (i != row) itemp++;
        }

        return minor;
    }

    @Override
    public double[][] operate(double[][] a, double[][] b) {
        return null;
    }

    @Override
    public double[][] operate(double[][] matrix, double c) {
        return new double[0][];
    }
}
