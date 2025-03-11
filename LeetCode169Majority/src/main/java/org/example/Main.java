package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{3,2,3}));
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> valueToCount = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (valueToCount.containsKey(nums[i])){
                valueToCount.put(nums[i], valueToCount.get(nums[i])+1);
            }
            else {
                //base case
                valueToCount.put(nums[i], 1);
            }
        }

        Map.Entry<Integer, Integer> maxItem = valueToCount.entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue())).get();
        return maxItem.getKey();
    }
}