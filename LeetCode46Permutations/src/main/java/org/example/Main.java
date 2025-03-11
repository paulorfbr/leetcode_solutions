package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute(new int[]{0,1}));
        System.out.println(permute(new int[]{1}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permuts = new ArrayList<>();
        generate(nums.length, nums, permuts);
        return permuts;
    }

    private static void generate(int k, int[] nums, List<List<Integer>> result) {
        if (k == 1) {
            List<Integer> perm = Arrays.stream(nums).boxed().collect(Collectors.toList());
            result.add(perm);
        }
        else
            // Generate permutations with k-th unaltered
            generate(k - 1, nums, result);

        // Generate permutations for k-th swapped with each k-1 initial
        for (int i=0; i < k-1; i++){
            // Swap choice dependent on parity of k (even or odd)
            int aux;
            if (k%2==0) {
                // zero-indexed, the k-th is at k-1
                aux = nums[i];
                nums[i] = nums[k-1];
            }
            else {
                aux = nums[0];
                nums[0] = nums[k-1];
            }
            nums[k-1] = aux;
            generate(k - 1, nums, result);
        }
    }
}