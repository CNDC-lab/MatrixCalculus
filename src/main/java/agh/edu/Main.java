package agh.edu;

import agh.edu.lab1.FileSaver;
import agh.edu.lab1.Generate;
import agh.edu.lab1.Matrix;


public class Main {
    public static void main(String[] args) throws Exception {
        Generate gen = new Generate();
        FileSaver saver = new FileSaver("result.csv");

        float[][] a = {{2, 2, 2, 1, 1, 5, 2, 2}, {3, 1, 5, 2, 2, 2, 2, 1}, {2, 3, 4, 4, 2, 2, 3, 3}, {2, 4, 2, 2, 3, 4, 3, 4},
                {2, 2, 2, 1, 2, 5, 2, 2}, {2, 2, 1, 2, 5, 2, 3, 3}, {2, 4, 2, 2, 3, 4, 3, 4}, {3, 1, 5, 2, 2, 2, 2, 1}};
        Matrix A = new Matrix(a);
        Matrix[] lu = A.recursiveLU();

        System.out.println(lu[0]);
        System.out.println(lu[1]);

        System.out.println(lu[0].binet_mul(lu[1]));

//        for(int k = 1 ; k <= 16 ; k++) {
//            Matrix A = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
//
//            long start = System.nanoTime();
//
//            A.recursiveInverse();
//
//            long end = System.nanoTime();
//            saver.saveRecord(k + ";" + Double.toString((end - start)/1000) + "\n");
//            System.out.println("DONE for k: " + k + " time: " + Double.toString((end - start)/1000));
//        }
    }
}

