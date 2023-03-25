package agh.edu;

import agh.edu.lab1.FileSaver;
import agh.edu.lab1.Generate;
import agh.edu.lab1.Matrix;


public class Main {
    public static void main(String[] args) throws Exception {
        Generate gen = new Generate();
        FileSaver saver = new FileSaver("result.csv");


        for(int k = 1 ; k <= 16 ; k++) {
            Matrix A = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));

            long start = System.nanoTime();

            A.recursiveInverse();

            long end = System.nanoTime();
            saver.saveRecord(k + ";" + Double.toString((end - start)/1000) + "\n");
            System.out.println("DONE for k: " + k + " time: " + Double.toString((end - start)/1000));
        }
//        float[][] a = {
//                {1,	2, 3, 2},
//                {5,	5, 6, 1},
//                {8,	8, 9, 10},
//                {4, 6, 3, 7}};
//
//        Matrix A = new Matrix(a);
//        System.out.println(A.recursiveInverse());
//        DONE for k: 1 time: 50.0
//        DONE for k: 2 time: 100.0
//        DONE for k: 3 time: 161.0
//        DONE for k: 4 time: 1061.0
//        DONE for k: 5 time: 5559.0
//        DONE for k: 6 time: 27146.0
//        DONE for k: 7 time: 134421.0
//        DONE for k: 8 time: 664470.0
//        DONE for k: 9 time: 4508953.0
    }
}

