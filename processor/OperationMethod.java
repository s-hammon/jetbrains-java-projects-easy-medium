package processor;

class OperationMethod {
    private Operation operation;

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double[][] operate(double[][] a, double[][] b) {
        return this.operation.operate(a, b);
    }

    public double[][] operate(double[][] matrix, double c) {
        return this.operation.operate(matrix, c);
    }

    public double operate(double[][] matrix) {
        return this.operation.operate(matrix);
    }
}