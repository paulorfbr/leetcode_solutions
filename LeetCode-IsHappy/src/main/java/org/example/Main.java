package org.example;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
    }

    public static boolean isHappy(int n) {
        Set<Integer> alreadyCalculatedItems = new HashSet<Integer>();
        int x = n;
        boolean isHappy = false;
        while(!alreadyCalculatedItems.contains(x)){
            int[] result = new int[1];
            alreadyCalculatedItems.add(x);
            isHappy = isHappy || isHappyAux(x, result);
            x = result[0];
        }
        return isHappy;
    }

    private static boolean isHappyAux(int n, int[] result){
        String s = ""+n;
        long sum = 0;
        for(int i=0;i<s.length();i++){
            int a = Character.getNumericValue(s.charAt(i));
            long a2 = (long)Math.pow(a,2);
            sum+=a2;
        }
        if (sum==1){
            return true;
        }
        result[0] = (int)sum;
        return false;
    }
}