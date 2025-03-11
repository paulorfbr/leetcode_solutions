package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public static int maxSubArray(int[] nums) {
        //Kadane's dynamic programming
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;
        for (int i=0; i< nums.length;i++){
            localMax = Integer.max(nums[i], nums[i] + localMax);
            if (localMax > globalMax){
                globalMax = localMax;
            }
        }
        return globalMax;
    }
}