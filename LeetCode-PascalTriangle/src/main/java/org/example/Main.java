package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(generate(5));
        System.out.println(generate(1));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        binomialCoeff(numRows, numRows, pascalTriangle);
        return pascalTriangle;
    }

    // Coefficient C(n, k) - dynamic programming
    private static void binomialCoeff(int n, int k, List<List<Integer>> pascalTriangle) {
        int C[][] = new int[n + 1][k + 1];

        //bottom up
        for (int i = 0; i <= n; i++) {
            List<Integer> coefficients = new ArrayList<>();
            for (int j = 0; j < Math.min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                }

                //C[1,1] = 1
                //C[2,1]=C[2,2]=1
                //C[3,1] = 1
                //C[3,2] = C[2,1]+C[2,2] = 2
                //C[3,3] = 1
                //C[4,1] = 1
                //C[4,2] = C[3,1]+C[3,2] = 1+2 = 3
                //C[4,3] = C[3,2]+C[3,3] = 2+1 = 3
                //C[4,4] = 1
                else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
                coefficients.add(C[i][j]);
            }
            if (!coefficients.isEmpty()) {
                pascalTriangle.add(coefficients);
            }
        }
    }
}