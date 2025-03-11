package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralOrder = new ArrayList<>();
        spiralPrint(matrix.length,matrix[0].length, matrix, spiralOrder);
        return spiralOrder;
    }

    public static void spiralPrint(int m, int n, int[][] a, List<Integer> spiralOrder){
        //https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/#approach-2-boundary-traversal-on-time-and-o1-auxiliary-space
        //Define the boundaries of the matrix with variables top, bottom, left, and right.
        int top = 0;
        int bottom = m-1;
        int left = 0;
        int right = n-1;

        // Iterate until all elements are printed
        while (top <= bottom && left <= right) {
            //Print the top row from left to right and increment top.
            for (int c=left;c<=right;c++){
                spiralOrder.add(a[top][c]);
            }
            top++;

            //Print the right column from top to bottom and decrement right.
            for (int r=top;r<=bottom;r++){
                spiralOrder.add(a[r][right]);
            }
            right--;

            //Check if boundaries have crossed; if not, print the bottom row from right to left and decrement bottom.
            if (top <= bottom) {
                for (int c = right; c >= left; --c) {
                    spiralOrder.add(a[bottom][c]);
                }
                bottom--;
            }

            //Print the left column from bottom to top and increment left.
            if (left <= right) {
                for (int r = bottom; r >= top; --r) {
                    spiralOrder.add(a[r][left]);
                }
                left++;
            }
        }
    }


}