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

    static float[][] add(final float[][] A, final float[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != colsB || rowsA != rowsB) throw new IllegalArgumentException("Matrix A and B must have same dimensions");

        float[][] result = new float[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                result[i][j] += A[i][j] + B[i][j];
            }
        }

        return result;
    }

    public Matrix binet_mul(final Matrix B, int l) throws Exception {
        if(this.cols != B.rows) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");
        Matrix ans = new Matrix(this.rows, B.cols);

        ans.matrix = binet_mul(this.getMatrix(), B.getMatrix(), l);
        return ans;
    }

    static float[][] binet_mul(final float[][] A, final float[][] B, int l) throws Exception{
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) throw new IllegalArgumentException("Matrix A cols must be equal matrix B rows");

        if(rowsA <= l && colsA <= l && colsB <= l){//matrix is small enough to mul it like normal
            return mul(A, B);
        }
        else{
            int aColHalf = 0, aRowHalf = 0, bColHalf = 0, bRowHalf = 0;
            if (colsA > l) aColHalf = colsA / 2;
            if (rowsA > l) aRowHalf = rowsA / 2;
            if (colsB > l) bColHalf = colsB / 2;
            if (rowsB > l) bRowHalf = rowsB / 2;
            if(aRowHalf != 0 && aColHalf == 0 && bColHalf == 0 && bRowHalf == 0){//just divide A horizontally
                Matrix[] split = sliceHorizontal(A, aRowHalf);
                return mergeVertical(binet_mul(split[0].getMatrix(), B, l), binet_mul(split[1].getMatrix(), B, l));
            }
            if(aRowHalf == 0 && aColHalf == 0 && bColHalf != 0 && bRowHalf == 0){//just divide B vertically
                Matrix[] split = sliceVertical(B, bColHalf);
                return mergeHorizontal(binet_mul(A, split[0].getMatrix(), l), binet_mul(A, split[1].getMatrix(), l));
            }
            if(aRowHalf != 0 && aColHalf == 0 && bColHalf != 0 && bRowHalf == 0){//A horizontally, B vertically
                Matrix[] splitA = sliceHorizontal(A, aRowHalf);
                Matrix[] splitB = sliceVertical(B, bColHalf);
                return mergeVertical(
                        mergeHorizontal(binet_mul(splitA[0].getMatrix(), splitB[0].getMatrix(), l), binet_mul(splitA[0].getMatrix(), splitB[1].getMatrix(), l)),
                        mergeHorizontal(binet_mul(splitA[1].getMatrix(), splitB[0].getMatrix(), l), binet_mul(splitA[1].getMatrix(), splitB[1].getMatrix(), l))
                );
            }
            if(aRowHalf == 0 && aColHalf != 0 && bColHalf == 0 && bRowHalf != 0){//A vertically, B horizontally
                Matrix[] splitA = sliceVertical(A, aColHalf);
                Matrix[] splitB = sliceHorizontal(B, bRowHalf);
                return add(binet_mul(splitA[0].getMatrix(), splitB[0].getMatrix(), l), binet_mul(splitA[1].getMatrix(), splitB[1].getMatrix(), l));
            }
            if(aRowHalf == 0 && aColHalf != 0 && bColHalf != 0 && bRowHalf != 0){//A vertically, B both
                Matrix[] splitA = sliceVertical(A, aColHalf);
                Matrix[] splitB = sliceBoth(B, bRowHalf, bColHalf);
                return add(
                        mergeHorizontal(binet_mul(splitA[0].getMatrix(), splitB[0].getMatrix(), l),binet_mul(splitA[0].getMatrix(), splitB[1].getMatrix(), l)),
                        mergeHorizontal(binet_mul(splitA[1].getMatrix(), splitB[2].getMatrix(), l),binet_mul(splitA[1].getMatrix(), splitB[3].getMatrix(), l))
                );
            }
            if(aRowHalf != 0 && aColHalf != 0 && bColHalf == 0 && bRowHalf != 0){//A both, B horizontally
                Matrix[] splitA = sliceBoth(A, aRowHalf, aColHalf);
                Matrix[] splitB = sliceHorizontal(B, bRowHalf);
                return add(
                        mergeVertical(binet_mul(splitA[0].getMatrix(), splitB[0].getMatrix(), l), binet_mul(splitA[2].getMatrix(), splitB[0].getMatrix(), l)),
                        mergeVertical(binet_mul(splitA[1].getMatrix(), splitB[1].getMatrix(), l), binet_mul(splitA[3].getMatrix(), splitB[1].getMatrix(), l))
                );
            }
            if(aRowHalf != 0 && aColHalf != 0 && bColHalf != 0 && bRowHalf != 0){//A both, B both
                Matrix[] splitA = sliceBoth(A, aRowHalf, aColHalf);
                Matrix[] splitB = sliceBoth(B, bRowHalf, bColHalf);
                return mergeVertical(
                        mergeHorizontal(
                                add(binet_mul(splitA[0].getMatrix(), splitB[0].getMatrix(), l),binet_mul(splitA[1].getMatrix(), splitB[2].getMatrix(), l)),
                                add(binet_mul(splitA[0].getMatrix(), splitB[1].getMatrix(), l),binet_mul(splitA[1].getMatrix(), splitB[3].getMatrix(), l))
                        ),
                        mergeHorizontal(
                                add(binet_mul(splitA[2].getMatrix(), splitB[0].getMatrix(), l),binet_mul(splitA[3].getMatrix(), splitB[2].getMatrix(), l)),
                                add(binet_mul(splitA[2].getMatrix(), splitB[1].getMatrix(), l),binet_mul(splitA[3].getMatrix(), splitB[3].getMatrix(), l))
                        )
                );
            }
            else{
                throw new Exception("Something went terribly wrong");
            }
        }
    }
    static public Matrix[] sliceVertical(final float[][] M, int sliceIndex) throws Exception{
        float[][] firstHalf = new float[M.length][sliceIndex];
        float[][] secondHalf = new float[M.length][M[0].length - sliceIndex];
        Matrix[] res = new Matrix[2];
        for(int i = 0 ; i < M.length; i++){
            System.arraycopy(M[i], 0, firstHalf[i], 0, sliceIndex);
        }
        for(int i = 0 ; i < M.length; i++){
            System.arraycopy(M[i], sliceIndex, secondHalf[i], 0, M[0].length - sliceIndex);
        }
        res[0] = new Matrix(firstHalf);
        res[1] = new Matrix(secondHalf);
        return res;
    }
    static public Matrix[] sliceHorizontal(final float[][] M, int sliceIndex) throws Exception{
        float[][] firstHalf = new float[sliceIndex][M[0].length];
        float[][] secondHalf = new float[M.length - sliceIndex][M[0].length];
        Matrix[] res = new Matrix[2];
        for(int i = 0 ; i < sliceIndex; i++){
            System.arraycopy(M[i], 0, firstHalf[i], 0, M[0].length);
        }
        for(int i = 0 ; i < M.length - sliceIndex; i++){
            System.arraycopy(M[i + sliceIndex], 0, secondHalf[i], 0, M[0].length);
        }
        res[0] = new Matrix(firstHalf);
        res[1] = new Matrix(secondHalf);
        return res;
    }
    static public Matrix[] sliceBoth(final float[][] M, int sliceRowIndex, int sliceColIndex) throws Exception{
        float[][] firstHalf = new float[sliceRowIndex][sliceColIndex];
        float[][] secondHalf = new float[sliceRowIndex][M[0].length - sliceColIndex];
        float[][] thirdHalf = new float[M.length - sliceRowIndex][sliceColIndex];
        float[][] forthHalf = new float[M.length - sliceRowIndex][M[0].length - sliceColIndex];
        Matrix[] res = new Matrix[4];
        for(int i = 0 ; i < sliceRowIndex; i++){
            System.arraycopy(M[i], 0, firstHalf[i], 0, sliceColIndex);
        }
        for(int i = 0 ; i < sliceRowIndex; i++){
            System.arraycopy(M[i], sliceColIndex, secondHalf[i], 0, M[0].length - sliceColIndex);
        }
        for(int i = 0 ; i < M.length - sliceRowIndex; i++){
            System.arraycopy(M[i + sliceRowIndex], 0, thirdHalf[i], 0, sliceColIndex);
        }
        for(int i = 0 ; i < M.length - sliceRowIndex; i++){
            System.arraycopy(M[i + sliceRowIndex], sliceColIndex, forthHalf[i], 0, M[0].length - sliceColIndex);
        }
        res[0] = new Matrix(firstHalf);
        res[1] = new Matrix(secondHalf);
        res[2] = new Matrix(thirdHalf);
        res[3] = new Matrix(forthHalf);
        return res;
    }

    static public float[][] mergeVertical(final float[][] A, final float[][] B){//merge two matrices bottom to top
        if (A[0].length != B[0].length) throw new IllegalArgumentException("Cannot vertically merge two matrices with different number of cols");
        float[][] res = new float[A.length + B.length][A[0].length];
        for(int i = 0 ; i < A.length; i++){
            System.arraycopy(A[i], 0, res[i], 0, A[0].length);
        }
        for(int i = 0 ; i < B.length; i++){
            System.arraycopy(B[i], 0, res[i + A.length], 0, B[0].length);
        }
        return res;

    }

    static public float[][] mergeHorizontal(final float[][] A, final float[][] B){//merge two matrices side to side
        if (A.length != B.length) throw new IllegalArgumentException("Cannot horizontally merge two matrices with different number of rows");
        float[][] res = new float[A.length][A[0].length + B[0].length];
        for(int i = 0 ; i < A.length; i++){
            System.arraycopy(A[i], 0, res[i], 0, A[0].length);
        }
        for(int i = 0 ; i < B.length; i++){
            System.arraycopy(B[i], 0, res[i], A[0].length, B[0].length);
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
