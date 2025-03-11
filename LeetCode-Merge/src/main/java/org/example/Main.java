package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        int m = 3;
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m-1;
        int i2 = n-1;
        int[] aux1 = new int[m];
        int mergeIndex = m+n-1;
        for (int i=0;i<m;i++){
            aux1[i]=nums1[i];
        }
        while(i1>=0 && i2>=0){
            if (aux1[i1]>=nums2[i2]){
                nums1[mergeIndex]=aux1[i1];
                i1--;
                mergeIndex--;
            }
            if (nums2[i2]>aux1[i1]){
                nums1[mergeIndex]=nums2[i2];
                i2--;
                mergeIndex--;
            }
        }
        while(i1>=0){
            nums1[mergeIndex]=aux1[i1];
            mergeIndex--;
            i1--;
        }
        while(i2>=0){
            nums1[mergeIndex]=nums2[i2];
            mergeIndex--;
            i2--;
        }
    }
}