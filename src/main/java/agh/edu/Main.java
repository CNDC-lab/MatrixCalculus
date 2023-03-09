package agh.edu;

import agh.edu.lab1.FileSaver;
import agh.edu.lab1.Generate;
import agh.edu.lab1.Matrix;


public class Main {
    public static void main(String[] args) throws Exception {
        Generate gen = new Generate();
        FileSaver saver = new FileSaver("result.csv");

        int l = (int) Math.pow(2, 2);
        for(int k = 1 ; k <= 9 ; k++){
            Matrix A = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
            Matrix B = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
            long start = System.nanoTime();

            System.out.println(A.binet_mul(B, l));
            long end = System.nanoTime();
            saver.saveRecord(k + ";" + Double.toString((end - start)/1000) + ";" + l + "\n");
        }
//        float[][] a = {{1,2,3},{4,5,6},{7,8,9}};
////        float[][] b = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        float[][] b = {{1,2,3},{4,5,6},{7,8,9}};
//        float[][] a = {{1,2,3,4},{5,6,7,8},{9,0,1,2},{3,4,5,6}};
//        float[][] b = {{1,2,3,4},{5,6,7,8},{9,0,1,2},{3,4,5,6}};
//        Matrix A = new Matrix(a);
//        System.out.println(A);
//        Matrix B = new Matrix(a);
//        System.out.println(B);
//
//        Matrix A = gen.generateSimple2DMatrix(14, 15);
//        Matrix B = gen.generateSimple2DMatrix(15, 16);
//
//        System.out.println("Regular mul result:");
//        System.out.println(A.mul(B));
//
//        System.out.println("Binet mul result:");
//        System.out.println(A.binet_mul(B, 2));

    }
}