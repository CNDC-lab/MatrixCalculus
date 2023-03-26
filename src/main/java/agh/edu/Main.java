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
    }
}

