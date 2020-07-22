package processor;

import java.util.Scanner;

class ProcessorMenu {
    Scanner sc = new Scanner(System.in);
    private static int row1;
    private static int row2;
    private static int col1;
    private static int col2;

    int mainMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate determinant");
        System.out.println("Invert matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
        return sc.nextInt();
    }

    void addMatrices() {
        System.out.println("Enter size of first matrix: ");
        row1 = sc.nextInt();
        col1 = sc.nextInt();
        System.out.println("Enter first matrix");
        double[][] a = inData(row1, col1);
        System.out.println("Enter size of second matrix: ");
        row2 = sc.nextInt();
        col2 = sc.nextInt();
        System.out.println("Enter second matrix");
        double[][] b = inData(row2, col2);
        if (row1 != row2 || col1 != col2) {
            System.out.println("Cannot add matrices. Try again.");
        } else {
            OperationMethod opMethod = new OperationMethod();
            opMethod.setOperation(new Addition());
            System.out.println("The addition result is:");
            outData(opMethod.operate(a, b));
        }
    }

    void scalarMultiply() {
        System.out.println("Enter size of the matrix: ");
        row1 = sc.nextInt();
        col1 = sc.nextInt();
        System.out.println("Enter matrix");
        double[][] a = inData(row1, col1);
        System.out.println("Enter constant: ");
        double constant = sc.nextInt();
        System.out.println("The scalar multiplication result is:");
        OperationMethod opMethod = new OperationMethod();
        opMethod.setOperation(new ScalarMultiplication());
        outData(opMethod.operate(a, constant));
    }

    void dotProduct() {
        System.out.println("Enter size of first matrix: ");
        row1 = sc.nextInt();
        col1 = sc.nextInt();
        System.out.println("Enter first matrix");
        double[][] a = inData(row1, col1);
        System.out.println("Enter size of second matrix: ");
        row2 = sc.nextInt();
        col2 = sc.nextInt();
        System.out.println("Enter second matrix");
        double[][] b = inData(row2, col2);
        System.out.println("The dot product is:");
        OperationMethod opMethod = new OperationMethod();
        opMethod.setOperation(new DotProduct());
        outData(opMethod.operate(a, b));
    }

    void transposeMatrix() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice: ");
        double c = sc.nextInt();
        System.out.println("Enter matrix size: ");
        row1 = sc.nextInt();
        col1 = sc.nextInt();
        System.out.println("Enter matrix");
        double[][] a = inData(row1, col1);
        System.out.println("The result is:");
        OperationMethod opMethod = new OperationMethod();
        opMethod.setOperation(new TransposeMatrix());
        outData(opMethod.operate(a, c));
    }

    void calculateDeterminant() {
        System.out.println("Enter size of the matrix: ");
        row1 = sc.nextInt();
        col1 = sc.nextInt();
        System.out.println("Enter matrix");
        double[][] a = inData(row1, col1);
        System.out.println("The result is:");
        OperationMethod opMethod = new OperationMethod();
        opMethod.setOperation(new CalculateDeterminant());
        outData(opMethod.operate(a));
    }

    void invertMatrix() {
        System.out.println("Enter size of the matrix: ");
        row1 = sc.nextInt();
        col1 = sc.nextInt();
        System.out.println("Enter matrix");
        double[][] a = inData(row1, col1);
        System.out.println("The result is:");
        OperationMethod opMethod = new OperationMethod();
        opMethod.setOperation(new InvertMatrix());
        outData(opMethod.operate(a, 1));
    }

    static double[][] inData(int n, int m) {
        Scanner sc = new Scanner(System.in);
        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            String[] row = sc.nextLine().trim().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Double.parseDouble(row[j]);
            }
        }

        return matrix;
    }

    static void outData(double[][] matrix) {
        for (double[] rows : matrix) {
            for (double value : rows) {
                System.out.print(value + " ");
            }

            System.out.println();
        }
    }

    static void outData(double result) {
        System.out.println(result);
    }
}