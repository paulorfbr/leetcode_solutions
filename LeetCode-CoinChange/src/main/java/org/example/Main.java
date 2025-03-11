package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
        //System.out.println(coinChange(new int[]{2}, 3));
        //System.out.println(coinChange(new int[]{1}, 0));
        //System.out.println(coinChange(new int[]{2,5,10,1}, 27));
        //System.out.println(coinChange(new int[]{186,419,83,408}, 6249));
    }

    public static int coinChange(int[] coins, int amount) {
        return minCoins(coins, coins.length, amount);
    }

    // Utility function for solving the minimum coins problem
    public static int minCoinsUtil(int[] coins, int m, int V, int[] dp)
    {
        // Base case: If target value V is 0, no coins are needed
        if (V == 0)
            return 0;

        // If subproblem is already solved, return the result from DP table
        if (dp[V] != -1)
            return dp[V];

        int res = Integer.MAX_VALUE;

        // Iterate over all coins and recursively solve for subproblems
        for (int i = 0; i < m; i++) {
            if (coins[i] <= V) {
                // Recursive call to solve for remaining
                // value V - coins[i]
                int sub_res = minCoinsUtil(coins, m, V - coins[i], dp);

                // If the subproblem has a valid solution
                // and the total number of coins is smaller
                // than the current result, update the
                // result
                if (sub_res != Integer.MAX_VALUE
                        && sub_res + 1 < res)
                    res = sub_res + 1;
            }
        }

        // Save the result in the DP table
        dp[V] = res;
        return res;
    }

    // Function to find the minimum number of coins needed to make a target value
    public static int minCoins(int[] coins, int m, int V)
    {
        // Create a DP table to store results of subproblems
        int[] dp = new int[V + 1];
        Arrays.fill(dp, -1); // Initialize DP table with -1

        // Call the utility function to solve the problem
        int result = minCoinsUtil(coins, m, V, dp);
        if (result==Integer.MAX_VALUE){
            return -1;
        }
        return result;
    }
}