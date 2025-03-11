package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(solveNQueens(1));
    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> currentSolution = new ArrayList<>();
        nQueens(0, n, currentSolution, board);
        return currentSolution;
    }

    public static void nQueens(int k, int n, List<List<String>> currentSolution, char[][] board) {
        if (k == n) {
            //found solution - add result to current solutions
            List<String> solutionFound = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuffer line = new StringBuffer();
                for (int j = 0; j < n; j++) {
                    line.append(board[i][j]);
                }
                solutionFound.add(line.toString());
            }
            currentSolution.add(solutionFound);
            return;
        }

        // try to add this queen in all rows one by one
        for (int i = 0; i < n; i++) {
            // Check if the queen can be placed on this place
            if (mayIMoveQueenHere(i, k, board)) {
                // Place this queen in board[i][col]
                board[i][k] = 'Q';

                //recursion - expand solution trying for next column
                nQueens(k + 1, n, currentSolution, board);

                // remove queen from board[i][col]
                board[i][k] = '.'; //backtracking
            }
        }

    }

    public static boolean mayIMoveQueenHere(int row, int col, char[][] board) {
        int r, c;
        //check horizontally
        for (c = 0; c < board.length; c++) {
            if (board[row][c] == 'Q') {
                return false; //cannot
            }
        }
        //check vertically
        for (r = 0; r < board.length; r++) {
            if (board[r][col] == 'Q') {
                return false; //cannot
            }
        }
        //check upper diagonal left side
        for (r = row, c = col; r >= 0 && c >= 0; r--, c--)
            if (board[r][c] == 'Q')
                return false;

        //check lower diagonal left side
        for (r = row, c = col; c >= 0 && r < board.length; r++, c--)
            if (board[r][c] == 'Q')
                return false;

        return true;
    }
}