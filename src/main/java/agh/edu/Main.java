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
        float[][] mat3 = {{2,2,3,1},{3,1,1,1},{1,1,1,2},{2,2,2,2}};
        float[][] mat4 = {
                {2,2,3,1,2,3,2,1},
                {3,1,1,1,2,3,2,1},
                {1,1,1,2,3,2,3,2},
                {2,2,2,2,2,1,2,3},
                {2,3,1,2,3,1,2,2},
                {3,2,3,2,1,2,1,1},
                {2,2,2,2,3,3,2,1},
                {3,1,3,2,2,2,2,1}};



        Matrix matrix = new Matrix(mat4);
        System.out.println(matrix.determinant());
        System.out.println(matrix.recursiveInverse());

//        Matrix{rows=4, cols=4,
//                matrix=[
// [0.0, 0.5, 0.0, -0.25]
// [-1.0, -0.5, -2.0, 2.75]
// [1.0, 0.0, 1.0, -1.5]
// [0.0, 0.0, 1.0, -0.5]
//] }

//        -101.0
//        Matrix{rows=8, cols=8,
//                matrix=[
// [-0.15841562, 0.3366336, -0.29702938, 0.01980114, 0.16831712, -0.11881137, -0.16831765, 0.30693072]
// [0.32673204, -0.06930703, 0.23762393, -0.41583896, 0.46534646, 0.49504948, -0.4653467, -0.4455446]
// [0.25742614, -0.29702973, -0.2673267, 0.21782005, -0.14851463, -0.30693048, 0.14851451, 0.37623766]
// [-0.54455435, -0.2178215, 0.60395956, -0.30693114, -0.108911425, 0.8415832, 0.10891239, -0.25742579]
// [-0.19801974, -0.079208255, -0.8712864, 0.52475214, -0.039603293, -1.1485137, 1.039602, 0.6336634]
// [-0.02970326, 0.188119, 0.069306135, 0.12871385, -0.4059412, 0.22772175, 0.40594226, -0.50495046]
// [0.5247526, 0.009901032, 1.1089113, -0.9405943, 0.5049506, 0.6435645, -1.5049508, -0.079208]
// [-0.12871301, 0.1485149, -0.366337, 0.89108944, -0.4257428, -0.3465351, 0.42574325, -0.18811876]
//] }

        double tmp = 0;

//        for(int i=0; i < 100000; i++){
//            long start = System.nanoTime();
//
//            long end = System.nanoTime();
//            tmp += (end - start)/1000;
//        }

//        System.out.println("DONE : " + Double.toString(tmp));
    }
}