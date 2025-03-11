package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(sortColors(new int[]{2,0,2,1,1,0}));
    }

    public static int[] sortColors(int[] nums) {
        quicksort(nums, 0, nums.length-1);
        return nums;
    }

    private static void quicksort(int[] nums, int left, int right){
        //partition under pivot
        int i = left;
        int j = right;
        int pivot = nums[(left+right)/2];
        while (i <= j){
            while (nums[i]<pivot) {
                //replace i index can advance
                i++;
            }
            while (nums[j]>pivot) {
                //replace j index can go down
                j--;
            }
            if (i <= j){
                //swap at i and j indexes - remove one out of order item
                int aux = nums[i];
                nums[i] = nums[j];
                nums[j] = aux;
                i++;
                j--;
            }
        }
        if (left < j){
            //sort remaining left items
            quicksort(nums, left, j);
        }
        if (i < right){
            //sort remaining right items
            quicksort(nums, i, right);
        }
    }
}