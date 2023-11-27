package org.example.algorithm;

import java.util.Arrays;

public class Leetcode64 {
    public int minPathSum(int[][] grid) {
        // return process(grid, grid.length-1, grid[0].length-1, grid[grid.length-1][grid[0].length-1]);
        int[] path = new int[grid[0].length];
        for(int j=0; j < grid[0].length; j++){
            path[j] = (j == 0 ? 0 : path[j-1]) + grid[0][j];
        }
        System.out.println(Arrays.toString(path));
        for(int i = 1; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                path[j] = Math.min(
                        grid[i][j] + (j == 0 ? path[j] : path[j-1]),
                        grid[i][j] + path[j]
                );
            }
            System.out.println(Arrays.toString(path));
        }
        return path[path.length-1];
    }

    private int process(int[][] grid, int i, int j, int sum){
        if(i < 0 || j < 0) return Integer.MAX_VALUE;
        if(i == 0 && j == 0) return sum;
        return Math.min(
                process(grid, i-1, j, sum+(i > 0 ? grid[i-1][j] : 0)),
                process(grid, i, j-1, sum+(j > 0 ? grid[i][j-1] : 0))
        );
    }

    public static void main(String[] args) {
        int[][] grid;
        grid = new int[][]{{1,2},{1,1}};
        System.out.println(new Leetcode64().minPathSum(grid));
    }
}
