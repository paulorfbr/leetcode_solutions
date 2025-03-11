package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(binarySearch2D(new int[][]{{5},{6}}, 5));
        //System.out.println(binarySearch2D(new int[][]{{-5}}, -5));
        //System.out.println(binarySearch2D(new int[][]{{1,1}}, 0));
        //System.out.println(binarySearch2D(new int[][]{{1,4},{2,5}}, 2));
        //System.out.println(binarySearch2D(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
    }

    public static boolean binarySearch2D(int[][] matrix, int target){
        //adapted from https://www.geeksforgeeks.org/searching-algorithms-for-a-2d-arrays-matrix/
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0, height = row * col - 1;

        int prevMid = 0;
        while (left <= height) {
            int mid = ( (row * col) %2 == 0) ? 1 + (left + (height - left) / 2) : (left + (height - left) / 2);
            int tC = mid % col; //transform matrix to flat array column
            int tR = mid / col; //transform matrix to flat array row
            int val = matrix[tR][tC];
            if (val == target)
                return true;
            if (mid==prevMid){
                break;
            }

            if (val < target) {
                prevMid = mid;
                left = mid + 1;
            }
            else {
                prevMid = mid;
                height = mid - 1;
            }
        }
        return false;
    }
}