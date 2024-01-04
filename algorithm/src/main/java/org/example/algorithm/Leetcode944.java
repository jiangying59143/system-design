package org.example.algorithm;

public class Leetcode944 {
    public int orangesRotting(int[][] grid) {
        int minutes = 0;
        while(true) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            boolean canRot = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 2 && !visited[i][j]) {
                        boolean up = rot(grid, i-1, j, visited);
                        boolean down = rot(grid, i+1, j, visited);
                        boolean left = rot(grid, i, j-1, visited);
                        boolean right = rot(grid, i, j+1, visited);
                        if(up || down || left || right) canRot = true;
                    }
                }
            }
            if(!canRot) break;
            minutes++;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return minutes;
    }



    private boolean rot(int[][] grid, int i, int j, boolean[][] visited){
        if(i < 0 || j <0 || i == grid.length || j == grid[i].length || grid[i][j] == 2 ||  grid[i][j] == 0 || visited[i][j]){
            return false;
        }
        grid[i][j] = 2;
        visited[i][j] = true;
        return true;
    }

    public static void main(String[] args) {
        int[][] grid;
        grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(new Leetcode944().orangesRotting(grid));
    }
}
