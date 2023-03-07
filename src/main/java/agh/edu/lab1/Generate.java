package agh.edu.lab1;

import java.util.Random;

public class Generate {
    private final Random random = new Random();

    public Matrix generate2DMatrix(int m, int n) throws Exception{
        float array[][] = new float[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = this.random.nextFloat();
            }
        }
        return new Matrix(array);
    }
}
