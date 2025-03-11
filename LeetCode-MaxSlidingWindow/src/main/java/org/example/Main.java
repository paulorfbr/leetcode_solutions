package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
        System.out.println(maxSlidingWindow(new int[]{1}, 1));
        System.out.println(maxSlidingWindow(new int[]{1,-1}, 1));
        System.out.println(maxSlidingWindow(new int[]{9,8,9,8}, 3));
    }

    public static List<Integer> maxSlidingWindow(int[] nums, int k){
        return Arrays.stream(maxSlidingWindowAux(nums,k)).boxed().collect(Collectors.toList());
    }

    private static int[] maxSlidingWindowAux(int[] nums, int k) {
        //sliding window always size of nums - k + 1
        int[] slidingWindow = new int[nums.length-k+1];
        int maxIndex = -1;
        int currentWindowMax = Integer.MIN_VALUE;
        for (int i=0;i<k;i++){
            if (nums[i]>=currentWindowMax){
                currentWindowMax = nums[i];
                maxIndex = i;
            }
        }
        slidingWindow[0]=currentWindowMax;
        int j=1;
        for (int i=k;i<nums.length;i++){
            //nums[i] entering item in window
            //nums[i-k] leaving item in window
            if (maxIndex>i-k){
                //leaving item was not in the max - check only new element
                if (nums[i]>=currentWindowMax){
                    currentWindowMax = nums[i];
                    maxIndex = i;
                }
            }
            else {
                //maxIndex<=i-k lost old max
                currentWindowMax = nums[i-k+1];
                for (int l=i-k+2;l<i+1;l++){
                    if (nums[l]>=currentWindowMax){
                        currentWindowMax = nums[l];
                        maxIndex = l;
                    }
                }
            }
            slidingWindow[j]=currentWindowMax;
            j++;
        }
        return slidingWindow;
    }
}