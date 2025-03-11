package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][] {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}}));
        System.out.println(numIslands(new char[][] {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}}));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        int numRows = grid.length;
        int numCols = grid[0].length;
        boolean[][] visited = new boolean[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    count += 1;
                    breathFirstSearch(row, col, visited, grid);
                }
            }
        }
        return count;
    }

    private static void breathFirstSearch(int row, int col, boolean[][] visited, char[][] grid){
        Queue<Pair> queue = new LinkedList<>();
        visited[row][col]=true;
        queue.offer(new Pair(row, col)); //initial item to search
        while(!queue.isEmpty()){
            Pair nextNode = queue.poll();
            //visit neighbours
            for (Pair v : myAdjacentList(nextNode.r,nextNode.c, grid)) {
                if (!visited[v.r][v.c]){
                    visited[v.r][v.c]=true;
                    //mark to next search if not yet done
                    queue.offer(v);
                }
            }
        }
    }

    private static boolean isLand(int row, int col, char[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == '1';
    }

    private static boolean isWater(int row, int col, char[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == '0';
    }

    private static List<Pair> myAdjacentList(int row, int col, char[][] grid){
        List<Pair> adjacentList = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (isLand(newRow, newCol, grid)){
                Pair p = new Pair(newRow, newCol);
                adjacentList.add(p);
            }
        }
        return adjacentList;
    }

    static class Pair {
        int r; //row
        int c; //column
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}