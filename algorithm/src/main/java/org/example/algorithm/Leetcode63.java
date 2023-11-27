package org.example.algorithm;

import java.util.Arrays;

public class Leetcode63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        return process(obstacleGrid, obstacleGrid.length-1, obstacleGrid[0].length-1);
        int[] path = new int[obstacleGrid[0].length];
        for (int j = 0; j < obstacleGrid[0].length; j++) {
            if(obstacleGrid[0][j] == 1 ) continue;
            path[j] = j == 0 ? 1 : path[j-1];
        }
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if(obstacleGrid[i][j] == 1 ){
                    path[j] = 0;
                    continue;
                }
                path[j] = (j == 0 ? 0 : path[j-1]) + path[j];
            }
        }
        return path[path.length-1];
    }

    private int process(int[][] obstacleGrid, int i, int j){
        if(i < 0 || j < 0) return 0;
        if(i == 0 && j == 0) return 1;
        if(obstacleGrid[i][j] == 1) return 0;
        return process(obstacleGrid, i-1, j) + process(obstacleGrid, i, j-1);
    }

    public static void main(String[] args) {
        int[][] obstacleGrid;
        obstacleGrid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(new Leetcode63().uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{{0,1},{0,0}};
        System.out.println(new Leetcode63().uniquePathsWithObstacles(obstacleGrid));
    }
}
