package agh.edu.lab1;

import java.util.Arrays;

public class Matrix {

    public Matrix(final int n, final int m) {
        this.rows = n;
        this.cols = m;
        this.matrix = new float[n][m];
    }

    public Matrix(final Matrix matrix) {
        this.rows = matrix.rows;
        this.cols = matrix.cols;
        this.matrix = matrix.matrix;
    }

    public Matrix(final float[][] matrix) throws Exception {
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

    static float[][] mul(final float[][] A, final float[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");

        float[][] result = new float[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public Matrix binet_mul(final Matrix B) throws Exception {
        if(this.cols != B.rows) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");
        Matrix ans = new Matrix(this.rows, B.cols);

        ans.matrix = binet_mul(this.getMatrix(), B.getMatrix());
        return ans;
    }

    static float[][] binet_mul(final float[][] A, final float[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");
        float[][] result = new float[rowsA][colsB];

        if(rowsA == 1 && colsB == 1){//if we only have vectors we multiply them as normal else divide
            return mul(A, B);
        }
        else{
            int aHalf = Math.max(rowsA / 2, 1);
            int bHalf = Math.max(colsB / 2, 1);
            if (aHalf == rowsA){ //we just need to divide B, A is already a vector
                float[][] firstHalf = new float[rowsB][bHalf];
                float[][] secondHalf = new float[rowsB][colsB - bHalf];


                for(int i = 0 ; i < rowsB; i++){
                    for(int j = 0 ; j < bHalf ; j++){
                        firstHalf[i][j] = B[i][j];
                    }
                }
                for(int i = 0 ; i < rowsB; i++){
                    for(int j = 0 ; j < colsB - bHalf ; j++){
                        secondHalf[i][j] = B[i][j + bHalf];
                    }
                }

                return mergeHorizontal(binet_mul(A,firstHalf),binet_mul(A,secondHalf));
            }
            if (bHalf == colsB){ //we just need to divide A, B is already a vector
                float[][] firstHalf = new float[aHalf][colsA];
                float[][] secondHalf = new float[rowsA - aHalf][colsA];
                for(int i = 0 ; i < aHalf; i++){
                    for(int j = 0 ; j < colsA ; j++){
                        firstHalf[i][j] = A[i][j];
                    }
                }
                for(int i = 0 ; i < rowsA - aHalf; i++){
                    for(int j = 0 ; j < colsA ; j++){
                        secondHalf[i][j] = A[i + aHalf][j];
                    }
                }
                return mergeVertical(binet_mul(firstHalf,B),binet_mul(secondHalf,B));
            }
            else{//neither of matrices are vectors, we need to divide them both
                float[][] firstHalf = new float[aHalf][colsA];
                float[][] secondHalf = new float[rowsA - aHalf][colsA];
                float[][] thirdHalf = new float[rowsB][bHalf];
                float[][] forthHalf = new float[rowsB][colsB - bHalf];
                for(int i = 0 ; i < aHalf; i++){
                    for(int j = 0 ; j < colsA ; j++){
                        firstHalf[i][j] = A[i][j];
                    }
                }
                for(int i = 0 ; i < rowsA - aHalf; i++){
                    for(int j = 0 ; j < colsA ; j++){
                        secondHalf[i][j] = A[i + aHalf][j];
                    }
                }
                for(int i = 0 ; i < rowsB; i++){
                    for(int j = 0 ; j < bHalf ; j++){
                        thirdHalf[i][j] = B[i][j];
                    }
                }
                for(int i = 0 ; i < rowsB; i++){
                    for(int j = 0 ; j < colsB - bHalf ; j++){
                        forthHalf[i][j] = B[i][j + bHalf];
                    }
                }
                return mergeVertical(mergeHorizontal(binet_mul(firstHalf, thirdHalf), binet_mul(firstHalf, forthHalf)),mergeHorizontal(binet_mul(secondHalf, thirdHalf),binet_mul(secondHalf, forthHalf)));
            }
        }
    }

    static public float[][] mergeVertical(final float[][] A, final float[][] B){//merge two matrices bottom to top
        if (A[0].length != B[0].length) throw new IllegalArgumentException("Cannot vertically merge two matrices with different number of cols");
        float[][] res = new float[A.length + B.length][A[0].length];
        for(int i = 0 ; i < A.length; i++){
            for(int j = 0 ; j < A[0].length ; j++){
                res[i][j] = A[i][j];
            }
        }
        for(int i = 0 ; i < B.length; i++){
            for(int j = 0 ; j < B[0].length ; j++){
                res[i + A.length][j] = B[i][j];
            }
        }
        return res;

    }

    static public float[][] mergeHorizontal(final float[][] A, final float[][] B){//merge two matrices side to side
        if (A.length != B.length) throw new IllegalArgumentException("Cannot horizontally merge two matrices with different number of rows");
        float[][] res = new float[A.length][A[0].length + B[0].length];
        for(int i = 0 ; i < A.length; i++){
            for(int j = 0 ; j < A[0].length ; j++){
                res[i][j] = A[i][j];
            }
        }
        for(int i = 0 ; i < B.length; i++){
            for(int j = 0 ; j < B[0].length ; j++){
                res[i][j + A[0].length] = B[i][j];
            }
        }
        return res;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String matrix = "";
        for(float[] arr : this.matrix){
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
    private float[][] matrix;
}
