package agh.edu.lab1;

import java.util.Arrays;

public class Matrix {

    public Matrix(final int n, final int m) {
        this.rows = n;
        this.cols = m;
        this.matrix = new int[n][m];
    }

    public Matrix(final Matrix matrix) {
        this.rows = matrix.rows;
        this.cols = matrix.cols;
        this.matrix = matrix.matrix;
    }

    public Matrix(final int[][] matrix) throws Exception {
        if(matrix.length == 0) throw new Exception("Bad argument");
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix mul(final Matrix B) throws Exception {
        if(this.cols != B.rows) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");
        Matrix ans = new Matrix(this.rows, B.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < B.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    ans.matrix[i][j] += this.matrix[i][k] * B.matrix[k][j];
                }
            }
        }

        return ans;
    }

    static int[][] mul(final int[][] A, final int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String matrix = "";
        for(int[] arr : this.matrix){
            matrix += " " + Arrays.toString(arr);
        }
        return "Matrix{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", matrix=[" + matrix +
                "] }";
    }

    private final int rows;
    private final int cols;
    private int[][] matrix;
}
