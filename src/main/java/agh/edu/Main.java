package agh.edu;

import agh.edu.lab1.FileSaver;
import agh.edu.lab1.Generate;
import agh.edu.lab1.Matrix;


public class Main {
    public static void main(String[] args) throws Exception {
//        Generate gen = new Generate();
//        FileSaver saver = new FileSaver("result.csv");
//
//
//        for(int k = 13 ; k <= 13 ; k++) {
//            for(int l = 4; l < 10; l++) {
//                Matrix A = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
//                Matrix B = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
//                long start = System.nanoTime();
//
//                A.binet_mul(B, (int) Math.pow(2, l));
//
//                long end = System.nanoTime();
//                saver.saveRecord(k + ";" + Double.toString((end - start)/1000) + ";" + Math.pow(2, l) + "\n");
//                System.out.println("DONE for k: " + k + ", l: " + l);
//            }
//        }

        float[][] mat = {{1,2,3,2},{5,5,6,1},{8,8,9,10},{4,6,3,7}};
        float[][] mat2 = {{1,2,1,2},{2,2,1,1},{1,1,1,2},{2,2,2,2}};
        float[][] mat3 = {{2,2},{3,1}};

        Matrix matrix = new Matrix(mat3);
        System.out.println(matrix.determinant());

        double tmp = 0;

        for(int i=0; i < 100000; i++){
            long start = System.nanoTime();
            matrix.recursiveInverse();
            long end = System.nanoTime();
            tmp += (end - start)/1000;
        }

        System.out.println("DONE : " + Double.toString(tmp));
    }
}