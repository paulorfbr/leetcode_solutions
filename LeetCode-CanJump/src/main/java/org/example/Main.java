package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,3,1,1,4}));
        System.out.println(canJump(new int[]{3,2,1,0,4}));
        System.out.println(canJump(new int[]{0}));
        System.out.println(canJump(new int[]{2,0}));
        System.out.println(canJump(new int[]{2,5,0,0}));
    }

    public static boolean canJump(int[] nums) {
        int currentIndex = 0;
        while (currentIndex < nums.length){
            if (currentIndex == nums.length-1) {
                return true;
            }
            if (nums[currentIndex]==0){
                return false;
            }
            //jump (greedy maximum possible) that do not reach end of array
            currentIndex += nums[currentIndex];
        }
        //reach end array
        return true;
    }
}