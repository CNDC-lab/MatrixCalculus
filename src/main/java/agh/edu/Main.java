package agh.edu;

import agh.edu.lab1.FileSaver;
import agh.edu.lab1.Generate;
import agh.edu.lab1.Matrix;


public class Main {
    public static void main(String[] args) throws Exception {
        Generate gen = new Generate();
        FileSaver saver = new FileSaver("result.csv");

        int l = 8;
        for(int k = 1 ; k <= 16 ; k++){
            Matrix A = gen.generate2DMatrix(k, k);
            Matrix B = gen.generate2DMatrix(k, k);
            long start = System.nanoTime();
            String method;
            if (k < l){
                System.out.println(A.mul(B));
                method = "classic";
            }
            else{
                System.out.println(A.binet_mul(B));
                method = "binet";
            }
            long end = System.nanoTime();
            saver.saveRecord(k + ";" + Double.toString((end - start)/1000) + ";" + method + "\n");
        }
//        float[][] a = {{1,2,3},{4,5,6},{7,8,9}};
////        float[][] b = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        float[][] b = {{1,2,3},{4,5,6},{7,8,9}};
//
//
//        Matrix A = new Matrix(a);
//        System.out.println(A);
//        Matrix B = new Matrix(b);
//        System.out.println(B);
//
//        System.out.println("Regular mul result:");
//        System.out.println(A.mul(B));
//
//        System.out.println("Binet mul result:");
//        System.out.println(A.binet_mul(B));
//        System.out.println(A);
    }
}