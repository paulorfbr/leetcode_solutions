package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> solutions = new ArrayList<>();
        List<Integer> initialSubset = new ArrayList<>();
        findSolutions(solutions, initialSubset, candidates, target, 0);
        return solutions;
    }

    private static void findSolutions(List<List<Integer>> solutions, List<Integer> subset, int[] candidates, int target, int index){
        if (isValidSolution(subset, target)){
            //store the solution
            List<Integer> sortedSubset = new ArrayList<>(subset.stream().sorted().collect(Collectors.toList()));
            if (!solutions.contains(sortedSubset)){
                solutions.add(sortedSubset);
            }
            return;
        }
        if (solutions.size()>=150){
            return;
        }

        for (int i=0;i<candidates.length;i++) {
            int candidate = candidates[i];
            int previousSum = subset.stream().mapToInt(Integer::intValue).sum();
            if (previousSum+candidate <= target) {
                //apply candidate
                List newSubset = new ArrayList();
                newSubset.addAll(subset);
                newSubset.add(index, candidate);
                findSolutions(solutions, newSubset, candidates, target, index+1);
                //backtrack (remove choice)
                newSubset.remove(index);
            }
        }
        return;
    }

    private static boolean isValidSolution(List<Integer> subset, int target) {
        int sum = subset.stream().mapToInt(Integer::intValue).sum();
        if (sum == target){
            return true;
        }
        return false;
    }
}