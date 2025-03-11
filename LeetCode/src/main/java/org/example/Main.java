package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = { 2,2,1 };
        int[] nums2 = { 4,1,2,1,2 };
        int[] nums3 = { 1 };
        int[] nums4 = { 1, 5, 1, 2, 3, 2, 3 };
        System.out.println(Main.singleNumber(nums1));
        System.out.println(Main.singleNumber(nums2));
        System.out.println(Main.singleNumber(nums3));
        System.out.println(Main.singleNumber(nums4));
    }

    public static int singleNumber(int[] nums) {
        Map<Integer,Integer> valueAndCount = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (!valueAndCount.containsKey(nums[i])){
                valueAndCount.put(nums[i],1);
            }
            else {
                valueAndCount.put(nums[i],2);
            }
        }

        Optional<Map.Entry<Integer,Integer>> value = valueAndCount.entrySet().stream().filter(e-> e.getValue().equals(1)).findFirst();
        if (value.isPresent()){
            return value.get().getKey().intValue();
        }
        return 0;
    }
}