package org.example;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(canJump(new int[]{2,0,0}));
    }

    public static boolean canJump(int[] nums) {
        return greedyJump(nums);
    }

    private static boolean greedyJump(int[] nums) {
        int furthestIndexReached = 0;
        if (nums.length == 1) {
            return true; //even if 0 reached end
        }
        for (int i = 0; i < nums.length; i++) {
            //update max reach greedly
            furthestIndexReached = Math.max(furthestIndexReached, i + nums[i]);
            if (furthestIndexReached >= nums.length - 1){
                return true;
            }
            if (nums[i] == 0 && furthestIndexReached == i + nums[i]) {
                //got stopped and could not bypass 0 item
                return false;
            }
        }
        //reached end exactly and it is positive or even passed end of array
        return true;
    }
}