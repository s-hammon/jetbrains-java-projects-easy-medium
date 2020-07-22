package processor;

interface Operation {

    double[][] operate(double[][] a, double[][] b);
    double[][] operate(double[][] matrix, double c);
    double operate(double[][] matrix);
}