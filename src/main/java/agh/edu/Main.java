package agh.edu;

import agh.edu.lab1.FileSaver;
import agh.edu.lab1.Generate;
import agh.edu.lab1.Matrix;


public class Main {
    public static void main(String[] args) throws Exception {
        Generate gen = new Generate();
        FileSaver saver = new FileSaver("result.csv");


        for(int k = 13 ; k <= 13 ; k++) {
            for(int l = 4; l < 10; l++) {
                Matrix A = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
                Matrix B = gen.generate2DMatrix((int) Math.pow(2, k), (int) Math.pow(2, k));
                long start = System.nanoTime();

                A.binet_mul(B, (int) Math.pow(2, l));

                long end = System.nanoTime();
                saver.saveRecord(k + ";" + Double.toString((end - start)/1000) + ";" + Math.pow(2, l) + "\n");
                System.out.println("DONE for k: " + k + ", l: " + l);
            }
        }
    }
}