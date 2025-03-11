package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(minDistance("", ""));
        System.out.println(minDistance("", "a"));
        System.out.println(minDistance("a", "b"));
        System.out.println(minDistance("ab", "cd"));
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));
    }

    public static int minDistance(String word1, String word2) {
        return editDistance(word1.toCharArray(), word2.toCharArray());
    }

    private static int editDistance(char[] s, char[] t) {
        //Wagner–Fischer algorithm
        int m = s.length > 0 ? s.length : 0;
        int n = t.length > 0 ? t.length : 0;

        // Create a table to store results of subproblems
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is
                // to insert all characters of second string
                if (i == 0) {
                    dp[i][j] = j; // Min. operations = j
                }

                // If second string is empty, only option is
                // to remove all characters of second string
                else if (j == 0) {
                    dp[i][j] = i; // Min. operations = i
                }

                // If last characters are same, ignore last
                // char and recur for remaining string
                else if (s[i-1] == t[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                // If the last character is different,
                // consider all possibilities and find the minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1], // Insert
                            dp[i - 1][j], // Remove
                            dp[i - 1][j - 1]); // Replace
            }
        }

        return dp[m][n];
    }

    static int min(int x, int y, int z)  {
        return Math.min(x, Math.min(y, z));
    }
}