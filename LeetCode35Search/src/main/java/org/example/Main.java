package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 4));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
    }

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right){
            int mid = left + (right-left/2);
            if (nums[mid]==target) {
                //found
                return mid;
            }
            else if (nums[mid] < target) {
                //ignore left part
                left = mid + 1;
            }
            else if (nums[mid] > target){
                //ignore right part
                right = mid - 1;
            }
        }
        //left>right : return insert point index
        return left;
    }
}