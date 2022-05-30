package heuristic_rupture.generator;

import java.util.Random;

public class MatrisUreteci {
    public static void main(String[] args) {
        int uzunluk = 100;
        Random r = new Random();
        int[][] a = new int[uzunluk][uzunluk];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == j) a[i][j] = 0;
                else a[i][j] = r.nextInt(2);
                System.out.print(a[i][j] + " ");
            }

            System.out.println();
        }

    }
}
