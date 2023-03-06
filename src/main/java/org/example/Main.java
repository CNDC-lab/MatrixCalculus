package org.example;

import org.example.lab1.Matrix;

public class Main {
    public static void main(String[] args) throws Exception {

        int[][] a = {{1,2,3},{4,5,6}};
        int[][] b = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};

        Matrix A = new Matrix(a);
        System.out.println(A);
        Matrix B = new Matrix(b);
        System.out.println(B);

        System.out.println(A.mul(B));
    }
}