package org.example.algorithm;

import java.util.Arrays;

public class Leetcode576 {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove+1];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process(m, n, maxMove, startRow, startColumn, dp);
    }

    private int process(int m, int n, int maxMove, int startRow, int startColumn, int[][][] dp){
        if(startRow < 0 || startRow == m || startColumn <0 || startColumn == n){
            return 1;
        }
        if(dp[startRow][startColumn][maxMove] != -1){
            return dp[startRow][startColumn][maxMove];
        }
        if(maxMove == 0){
            dp[startRow][startColumn][maxMove] = 0;
            return 0;
        }
        int ans = 0;
        ans += process(m, n, maxMove-1, startRow-1, startColumn, dp);
        ans += process(m, n, maxMove-1, startRow+1, startColumn, dp);
        ans += process(m, n, maxMove-1, startRow, startColumn-1, dp);
        ans += process(m, n, maxMove-1, startRow, startColumn+1, dp);
        dp[startRow][startColumn][maxMove] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode576().findPaths(2,2,2, 0,0));
    }
}
